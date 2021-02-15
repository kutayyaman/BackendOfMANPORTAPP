package com.kutay.MANPORT.ws;

import com.kutay.MANPORT.ws.domain.*;
import com.kutay.MANPORT.ws.dto.UserDTO;
import com.kutay.MANPORT.ws.repository.*;
import com.kutay.MANPORT.ws.service.IUserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
public class WsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WsApplication.class, args);
    }

    @Bean
//ModelMapper nesnesinden 1 tane olustur ve springin IoC containerina koy demis oluyoruz ve boylelikle dependency injection ile direkt alip kullanabilcez.
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); //ENUM'lari convert ederken sikinti vermemesi icn bu gerekliymis
        return modelMapper;
    }

    //, CountryRepository countryRepository, PlantRepository plantRepository, ServerRoomRepository serverRoomRepository, BackendRepository backendRepository, FrontendRepository frontendRepository, DatabaseRepository databaseRepository, TeamRepository teamRepository, ApplicationRepository applicationRepository, JobRepository jobRepository
    @Bean
    CommandLineRunner createInitialUsers(IUserService userService, IssueRepository issueRepository, CountryRepository countryRepository, ServerRepository serverRepository, ApplicationRepository applicationRepository, JobInterfaceRepository jobInterfaceRepository, JobImplementsRepository jobImplementsRepository) { //Spring projesi ayaga kalktigi zaman otomatik olarak bunun run methodu çalışır ve biz bunu başlangıç verileri eklemek için kullanıcaz.
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                createUsers();
                createCountries();
                createServers();
                createApplications();
                createJobInterfaces();
                createJobImplements();
                createIssues();
            }

            private void createJobImplements() {
                if (jobImplementsRepository.count() <= 0) {
                    List<JobImplement> jobImplements = new ArrayList<>();
                    List<JobInterface> jobInterfaces = jobInterfaceRepository.findAll();
                    List<Server> serverList = serverRepository.findAll();
                    for (int i = 0; i < 5; i++) {
                        JobImplement jobImplement = new JobImplement();
                        jobImplement.setJobInterface(jobInterfaces.get(i));
                        jobImplement.setServer(serverList.get(i));
                        jobImplements.add(jobImplement);
                    }
                    JobImplement jobImplement = new JobImplement();
                    jobImplement.setJobInterface(jobInterfaces.get(5));
                    jobImplement.setServer(serverList.get(1));
                    jobImplements.add(jobImplement);
                    jobImplementsRepository.saveAll(jobImplements);
                }
            }

            private void createJobInterfaces() {
                if (jobInterfaceRepository.count() <= 0) {
                    List<JobInterface> jobInterfaces = new ArrayList<>();
                    List<Application> applicationList = applicationRepository.findAll();
                    for (int i = 0; i < 5; i++) {
                        JobInterface jobInterface = new JobInterface();
                        jobInterface.setApplication(applicationList.get(i));
                        jobInterface.setName("Job1");
                        jobInterfaces.add(jobInterface);
                    }
                    JobInterface jobInterface = new JobInterface();
                    jobInterface.setApplication(applicationList.get(1));
                    jobInterface.setName("Job2");
                    jobInterfaces.add(jobInterface);
                    jobInterfaceRepository.saveAll(jobInterfaces);
                }
            }

            private void createApplications() {
                if (applicationRepository.count() <= 0) {
                    List<Application> applicationList = new ArrayList<>();
                    for (int i = 0; i < 5; i++) {
                        Application application = new Application();
                        application.setFullName("Application" + i);
                        application.setShortName("App" + i);
                        applicationList.add(application);
                    }
                    applicationRepository.saveAll(applicationList);
                }
            }

            private void createServers() {
                List<Country> countryList = countryRepository.findAll();
                List<Server> serverList = new ArrayList<>();
                if (serverRepository.count() <= 0) {
                    for (int i = 0; i < 5; i++) {
                        Server server = new Server();
                        server.setCountry(countryList.get(i));
                        server.setName("Prod" + i);
                        serverList.add(server);
                    }
                    serverRepository.saveAll(serverList);
                }
            }

            private void createCountries() {
                if (countryRepository.count() <= 0) {
                    List<Country> countryList = new ArrayList<>();
                    for (int i = 0; i < 5; i++) {
                        Country country = new Country();
                        country.setName("Country" + i);
                        countryList.add(country);
                    }
                    countryRepository.saveAll(countryList);
                }
            }

            private void createUsers() {
                if (userService.findByEmail("yamankutay1@gmail.com") == null) {
                    UserDTO user = new UserDTO();
                    user.setEmail("yamankutay1@gmail.com");
                    user.setPassword("12345678");
                    user.setName("kutay");
                    user.setSurname("yaman");
                    userService.save(user);
                }
            }


            private void createIssues() throws InterruptedException {
                if (issueRepository.count() <= 0) {
                    List<Issue> issueLists = new ArrayList<>();
                    //List<Job> jobList = jobRepository.findAll();
                    //List<Application> applicationList = applicationRepository.findAll();
                    for (int i = 0; i < 10; i++) {
                        Issue issue = new Issue();
                        issue.setName("Issue" + (i + 1));
                        issue.setDescription("Issue" + (i + 1) + "'in sorunu var");
                        issue.setImpact("Etki" + (i + 1) + " (BU IMPACT TAM OLARAK NEYDI BAK BELKI ENUM olması gerebilir.)");
                        //issue.setJob(jobList.get(i));
                        //issue.setApplication(applicationList.get(i));
                        issueLists.add(issue);
                        Thread.sleep(1000);
                    }
                    issueRepository.saveAll(issueLists);
                }
            }
        };
    }
}
