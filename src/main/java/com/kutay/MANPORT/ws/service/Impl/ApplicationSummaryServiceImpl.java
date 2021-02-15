package com.kutay.MANPORT.ws.service.Impl;

import com.kutay.MANPORT.ws.domain.*;
import com.kutay.MANPORT.ws.models.*;
import com.kutay.MANPORT.ws.repository.ApplicationRepository;
import com.kutay.MANPORT.ws.service.IApplicationSummaryService;
import com.kutay.MANPORT.ws.service.IServerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class ApplicationSummaryServiceImpl implements IApplicationSummaryService {

    private final ApplicationRepository applicationRepository;
    private final IServerService serverService;
    private final ModelMapper modelMapper;

    public ApplicationSummaryServiceImpl(ApplicationRepository applicationRepository, IServerService serverService, ModelMapper modelMapper) {
        this.applicationRepository = applicationRepository;
        this.serverService = serverService;
        this.modelMapper = modelMapper;
    }

    @Override
    public GetApplicationsSummaryModel getApplicationsSummary() {
        List<Application> applicationListOfAll = applicationRepository.findAll();
        GetApplicationsSummaryModel result = new GetApplicationsSummaryModel();


        result = getServerDatasWithCountrFromApplications(applicationListOfAll);
        return result;
    }

    private GetApplicationsSummaryModel getServerDatasWithCountrFromApplications(List<Application> applicationListOfAll) {
        GetApplicationsSummaryModel result = new GetApplicationsSummaryModel();
        for (Application application : applicationListOfAll) {
            List<Country> countryListOfApplication = findCountryListOfApplication(application);
            DataForGetApplicationsSummaryModel dataForResultList = new DataForGetApplicationsSummaryModel();
            for (Country countryOfApplication : countryListOfApplication) {
                List<Server> serverListOfApplicationInACountry = findServerListOfApplicationInACountry(application, countryOfApplication);
                ACountryWithServers aCountryWithServers = new ACountryWithServers();
                aCountryWithServers.setCountryId(countryOfApplication.getId());
                aCountryWithServers.setCountryName(countryOfApplication.getName());
                for (Server server : serverListOfApplicationInACountry) {
                    JobNameListInAServerModel jobNameListInAServerModel = new JobNameListInAServerModel();
                    jobNameListInAServerModel.setServerId(server.getId());
                    jobNameListInAServerModel.setServerName(server.getName());
                    List<JobImplement> jobImplements = findJobImplementsListOfApplicationInAServer(application, server);
                    for (JobImplement jobImplement : jobImplements) {
                        JobInterface jobInterface = jobImplement.getJobInterface();
                        String jobName = jobInterface.getName();
                        jobNameListInAServerModel.getJobNames().add(jobName);
                    }
                    aCountryWithServers.getJobNameListInAServerModelList().add(jobNameListInAServerModel);
                }
                dataForResultList.setAppId(application.getId());
                dataForResultList.setAppName(application.getShortName());
                dataForResultList.getACountryWithServersList().add(aCountryWithServers);

            }
            result.getApplicationsSummary().add(dataForResultList);
        }
        return result;
    }

    private List<JobImplement> findJobImplementsListOfApplicationInAServer(Application application, Server server) {
        List<JobImplement> result = new ArrayList<>();
        List<JobImplement> jobImplementsInServer = server.getJobImplements();
        List<JobInterface> jobInterfacesOfApplication = application.getJobInterfaces();

        for (JobImplement jobImplement : jobImplementsInServer) {
            for (JobInterface jobInterface : jobInterfacesOfApplication) {
                if (jobInterface.getId() == jobImplement.getJobInterface().getId()) {
                    result.add(jobImplement);
                    break;
                }
            }
        }

        return result;
    }

    private List<Country> findCountryListOfApplication(Application application) {
        HashSet<Country> countries = new HashSet<>();
        List<Country> countryList = new ArrayList<>();
        List<JobInterface> jobInterfaces = application.getJobInterfaces();
        JobInterface jobInterface = jobInterfaces.get(0); //zaten bir sunucuda bu app bulunuyorsa bu app'in butun interfaceleri olmak zorunda o yuzden 1 tanesine baksam yeterli.
        List<JobImplement> jobImplements = jobInterface.getJobImplements();
        for (JobImplement jobImplement : jobImplements) {
            countries.add(jobImplement.getServer().getCountry());
        }

        for (Country country : countries) {
            countryList.add(country);
        }

        return countryList;
    }

    private List<Server> findServerListOfApplicationInACountry(Application application, Country country) {
        List<Server> serverListByCountry = serverService.findAllByCountry(country);
        List<JobInterface> jobInterfacesOfApplication = application.getJobInterfaces();

        List<Server> result = new ArrayList<>();

        for (Server server : serverListByCountry) {
            List<JobImplement> jobImplementsFromServer = server.getJobImplements();
            for (JobImplement jobImplement : jobImplementsFromServer) {
                boolean isBreak = false;
                for (JobInterface jobInterfaceFromApplication : jobInterfacesOfApplication) {
                    if (jobInterfaceFromApplication.getId() == jobImplement.getJobInterface().getId()) {
                        result.add(server);
                        isBreak = true;
                        break;
                    }
                }
                if (isBreak) {
                    isBreak = false;
                    break;
                }
            }
        }

        return result;
    }

    private List<String> getNameListOfServers(List<Server> serverList) {
        List<String> serverNameList = new ArrayList<>();
        for (Server server : serverList) {
            serverNameList.add(server.getName());
        }
        return serverNameList;
    }

    private List<String> getNameListOfCountries(List<Country> countryList) {
        List<String> countryNameList = new ArrayList<>();
        for (Country country : countryList) {
            countryNameList.add(country.getName());
        }
        return countryNameList;
    }


}
