package com.kutay.MANPORT.ws.service.Impl;

import com.kutay.MANPORT.ws.domain.*;
import com.kutay.MANPORT.ws.dto.IssueDTO;
import com.kutay.MANPORT.ws.models.*;
import com.kutay.MANPORT.ws.repository.ApplicationRepository;
import com.kutay.MANPORT.ws.repository.IssueRepository;
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
    private final IssueRepository issueRepository;

    public ApplicationSummaryServiceImpl(ApplicationRepository applicationRepository, IServerService serverService, ModelMapper modelMapper, IssueRepository issueRepository) {
        this.applicationRepository = applicationRepository;
        this.serverService = serverService;
        this.modelMapper = modelMapper;
        this.issueRepository = issueRepository;
    }

    @Override
    public GetApplicationsSummaryModel getApplicationsSummary() {
        List<Application> applicationListOfAll = applicationRepository.findAll();
        GetApplicationsSummaryModel result;


        result = getServerDatasWithCountrFromApplications(applicationListOfAll);
        return result;
    }

    private GetApplicationsSummaryModel getServerDatasWithCountrFromApplications(List<Application> applicationListOfAll) {
        GetApplicationsSummaryModel result = new GetApplicationsSummaryModel();
        for (Application application : applicationListOfAll) {
            List<Country> countryListOfApplication = findCountryListOfApplication(application);
            DataForGetApplicationsSummaryModel dataForResultList = new DataForGetApplicationsSummaryModel();

            ImpactType highestImpactTypeOfApp = null;

            for (Country countryOfApplication : countryListOfApplication) {
                List<Server> serverListOfApplicationInACountry = findServerListOfApplicationInACountry(application, countryOfApplication);
                ACountryWithServers aCountryWithServers = new ACountryWithServers();
                aCountryWithServers.setCountryId(countryOfApplication.getId());
                aCountryWithServers.setCountryName(countryOfApplication.getName());

                ImpactType highestImpactTypeOfCountry = null;

                for (Server server : serverListOfApplicationInACountry) {
                    JobNameListInAServerModel jobNameListInAServerModel = new JobNameListInAServerModel();
                    jobNameListInAServerModel.setServerId(server.getId());
                    jobNameListInAServerModel.setServerName(server.getName());
                    List<JobImplement> jobImplements = findJobImplementsListOfApplicationInAServer(application, server);

                    ImpactType highestImpactTypeOfServer = null;
                    for (JobImplement jobImplement : jobImplements) {
                        JobInterface jobInterface = jobImplement.getJobInterface();
                        String jobName = jobInterface.getName();

                        JobNameAndIdWithIssuesModel jobNameAndIssuesModel = new JobNameAndIdWithIssuesModel();
                        jobNameAndIssuesModel.setJobName(jobName);
                        jobNameAndIssuesModel.setJobId(jobImplement.getId());
                        List<Issue> issues = findIssuesByJobImplement(jobImplement);

                        ImpactType highestImpactTypeOfJob = null;
                        for (Issue issue : issues) {
                            IssueDTO issueDTO = new IssueDTO();
                            issueDTO.setImpact(issue.getImpactType().toString());
                            issueDTO.setDescription(issue.getDescription());
                            issueDTO.setName(issue.getName());
                            jobNameAndIssuesModel.getIssueDTOList().add(issueDTO);

                            ImpactType impactTypeOfIssue = issue.getImpactType();
                            if (highestImpactTypeOfJob == null) {
                                highestImpactTypeOfJob = impactTypeOfIssue;
                            } else if (impactTypeOfIssue.getImpactCode() > highestImpactTypeOfJob.getImpactCode()) {
                                highestImpactTypeOfJob = impactTypeOfIssue;
                            }
                        }
                        if (highestImpactTypeOfJob != null) {
                            jobNameAndIssuesModel.setHighestImpactOfJob(highestImpactTypeOfJob.toString());
                        }
                        jobNameListInAServerModel.getJobAndIssues().add(jobNameAndIssuesModel);
                        if (highestImpactTypeOfJob != null) {
                            if (highestImpactTypeOfServer == null) {
                                highestImpactTypeOfServer = highestImpactTypeOfJob;
                            } else if (highestImpactTypeOfJob.getImpactCode() > highestImpactTypeOfServer.getImpactCode()) {
                                highestImpactTypeOfServer = highestImpactTypeOfJob;
                            }
                        }

                    }
                    if (highestImpactTypeOfServer != null) {
                        jobNameListInAServerModel.setHighestImpactOfServer(highestImpactTypeOfServer.toString());
                    }
                    aCountryWithServers.getJobNameListInAServerModelList().add(jobNameListInAServerModel);
                    if (highestImpactTypeOfServer != null) {
                        if (highestImpactTypeOfCountry == null) {
                            highestImpactTypeOfCountry = highestImpactTypeOfServer;
                        } else if (highestImpactTypeOfServer.getImpactCode() > highestImpactTypeOfCountry.getImpactCode()) {
                            highestImpactTypeOfCountry = highestImpactTypeOfServer;
                        }
                    }
                }
                dataForResultList.setAppId(application.getId());
                dataForResultList.setAppName(application.getShortName());
                if (highestImpactTypeOfCountry != null) {
                    aCountryWithServers.setHighestImpactOfCountry(highestImpactTypeOfCountry.toString());
                }
                dataForResultList.getACountryWithServersList().add(aCountryWithServers);

                if (highestImpactTypeOfCountry != null) {
                    if (highestImpactTypeOfApp == null) {
                        highestImpactTypeOfApp = highestImpactTypeOfCountry;
                    } else if (highestImpactTypeOfCountry.getImpactCode() > highestImpactTypeOfApp.getImpactCode()) {
                        highestImpactTypeOfApp = highestImpactTypeOfCountry;
                    }
                }

            }
            if (highestImpactTypeOfApp != null) {
                dataForResultList.setHighestImpactOfApp(highestImpactTypeOfApp.toString());
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

    private List<Issue> findIssuesByJobImplement(JobImplement jobImplement) {
        return issueRepository.findAllByJobImplement(jobImplement);
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
