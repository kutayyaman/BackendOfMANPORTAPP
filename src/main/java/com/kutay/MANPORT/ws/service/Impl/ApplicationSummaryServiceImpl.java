package com.kutay.MANPORT.ws.service.Impl;

import com.kutay.MANPORT.ws.domain.*;
import com.kutay.MANPORT.ws.dto.IssueDTO;
import com.kutay.MANPORT.ws.models.*;
import com.kutay.MANPORT.ws.repository.ApplicationRepository;
import com.kutay.MANPORT.ws.repository.IssueRepository;
import com.kutay.MANPORT.ws.service.IApplicationCountryService;
import com.kutay.MANPORT.ws.service.IApplicationSummaryService;
import com.kutay.MANPORT.ws.service.IServerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationSummaryServiceImpl implements IApplicationSummaryService {

    private final ApplicationRepository applicationRepository;
    private final IServerService serverService;
    private final ModelMapper modelMapper;
    private final IssueRepository issueRepository;
    private final IApplicationCountryService applicationCountryService;

    public ApplicationSummaryServiceImpl(ApplicationRepository applicationRepository, IServerService serverService, ModelMapper modelMapper, IssueRepository issueRepository, IApplicationCountryService applicationCountryService) {
        this.applicationRepository = applicationRepository;
        this.serverService = serverService;
        this.modelMapper = modelMapper;
        this.issueRepository = issueRepository;
        this.applicationCountryService = applicationCountryService;
    }

    @Override
    public GetApplicationsSummaryModel getApplicationsSummary() {
        List<Application> applicationListOfAll = applicationRepository.findAllByRowStatusAndTrack(RowStatus.ACTIVE, true);
        GetApplicationsSummaryModel result;

        result = getServerDatasWithCountrFromApplications(applicationListOfAll);
        result = sortApplicationsByIssue(result);
        return result;
    }

    private GetApplicationsSummaryModel sortApplicationsByIssue(GetApplicationsSummaryModel result) {
        GetApplicationsSummaryModel tempResult = result;
        List<AnAppWithCountriesModel> appListOriginal = result.getApplicationsSummary();
        List<AnAppWithCountriesModel> sortedAppList = sortAppListByIssue(appListOriginal);

        tempResult.setApplicationsSummary(sortedAppList);
        return tempResult;
    }

    private List<AnAppWithCountriesModel> sortAppListByIssue(List<AnAppWithCountriesModel> appList) {
        List<AnAppWithCountriesModel> appsHaveIssue = new ArrayList<>();
        List<AnAppWithCountriesModel> appsNotHaveIssue = new ArrayList<>();
        for (AnAppWithCountriesModel app : appList) {
            if (app.getHighestImpactOfApp() == null) {
                appsNotHaveIssue.add(app);
            } else {
                appsHaveIssue.add(app);
            }
        }

        appsHaveIssue.addAll(appsNotHaveIssue);
        return appsHaveIssue;
    }

    private GetApplicationsSummaryModel getServerDatasWithCountrFromApplications(List<Application> applicationListOfAll) {
        GetApplicationsSummaryModel result = new GetApplicationsSummaryModel();

        for (Application application : applicationListOfAll) {
            List<Country> countryListOfApplication = findCountryListOfApplication(application);
            AnAppWithCountriesModel anAppWithCountriesModel = new AnAppWithCountriesModel();

            ImpactType highestImpactTypeOfApp = null;

            for (Country countryOfApplication : countryListOfApplication) {
                List<Server> serverListOfApplicationInACountry = findServerListOfApplicationInACountry(application, countryOfApplication);
                ACountryWithServers aCountryWithServers = new ACountryWithServers();
                aCountryWithServers.setCountryId(countryOfApplication.getId());
                aCountryWithServers.setCountryName(countryOfApplication.getName());

                ImpactType highestImpactTypeOfCountry = null;

                for (Server server : serverListOfApplicationInACountry) {
                    AServerWithJobsModel aServerWithJobsModel = new AServerWithJobsModel();
                    aServerWithJobsModel.setServerId(server.getId());
                    aServerWithJobsModel.setServerName(server.getName());
                    List<JobImplement> jobImplements = findJobImplementsListOfApplicationInAServer(application, server);

                    ImpactType highestImpactTypeOfServer = null;

                    for (JobImplement jobImplement : jobImplements) {
                        JobInterface jobInterface = jobImplement.getJobInterface();
                        String jobName = jobInterface.getName();

                        AJobWithIssuesModel aJobWithIssuesModel = new AJobWithIssuesModel();
                        aJobWithIssuesModel.setJobName(jobName);
                        aJobWithIssuesModel.setJobId(jobImplement.getId());
                        List<Issue> issues = findIssuesByJobImplement(jobImplement);

                        ImpactType highestImpactTypeOfJob = null;

                        for (Issue issue : issues) {
                            if(issue.getTrack()){
                                IssueDTO issueDTO = setIssueToIssueDTO(issue);
                                aJobWithIssuesModel.getIssueDTOList().add(issueDTO);

                                highestImpactTypeOfJob = getHighestImpactType(highestImpactTypeOfJob, issue.getImpactType());
                            }

                        }
                        if (highestImpactTypeOfJob != null) {
                            aJobWithIssuesModel.setHighestImpactOfJob(highestImpactTypeOfJob.toString());
                        }
                        aServerWithJobsModel.getJobAndIssues().add(aJobWithIssuesModel);
                        highestImpactTypeOfServer = getHighestImpactType(highestImpactTypeOfServer, highestImpactTypeOfJob);

                    }
                    if (highestImpactTypeOfServer != null) {
                        aServerWithJobsModel.setHighestImpactOfServer(highestImpactTypeOfServer.toString());
                    }
                    aCountryWithServers.getAServerWithJobsList().add(aServerWithJobsModel);
                    highestImpactTypeOfCountry = getHighestImpactType(highestImpactTypeOfCountry, highestImpactTypeOfServer);
                }
                anAppWithCountriesModel.setAppId(application.getId());
                anAppWithCountriesModel.setAppName(application.getShortName());
                if (highestImpactTypeOfCountry != null) {
                    aCountryWithServers.setHighestImpactOfCountry(highestImpactTypeOfCountry.toString());
                }
                anAppWithCountriesModel.getACountryWithServersList().add(aCountryWithServers);

                highestImpactTypeOfApp = getHighestImpactType(highestImpactTypeOfApp, highestImpactTypeOfCountry);

            }
            if (highestImpactTypeOfApp != null) {
                anAppWithCountriesModel.setHighestImpactOfApp(highestImpactTypeOfApp.toString());
            }
            result.getApplicationsSummary().add(anAppWithCountriesModel);
        }
        return result;
    }

    private ImpactType getHighestImpactType(ImpactType highestImpactType, ImpactType impactType) {
        if (impactType != null) {
            if (highestImpactType == null) {
                highestImpactType = impactType;
            } else if (impactType.getImpactCode() > highestImpactType.getImpactCode()) {
                highestImpactType = impactType;
            }
        }
        return highestImpactType;
    }

    private IssueDTO setIssueToIssueDTO(Issue issue) {
        IssueDTO issueDTO = new IssueDTO();
        issueDTO.setImpact(issue.getImpactType().toString());
        issueDTO.setDescription(issue.getDescription());
        issueDTO.setName(issue.getName());
        return issueDTO;
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
        List<Country> countryList = new ArrayList<>();

        List<ApplicationCountry> applicationCountries = applicationCountryService.findAllByApplication(application);
        for (ApplicationCountry applicationCountry : applicationCountries) {
            if (applicationCountry.isTrack() && applicationCountry.getCount() > 0)
                countryList.add(applicationCountry.getCountry());
        }

        return countryList;

        /*HashSet<Country> countries = new HashSet<>();
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

        return countryList;*/
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
        return issueRepository.findAllByJobImplementAndRowStatus(jobImplement, RowStatus.ACTIVE);
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
