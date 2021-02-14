package com.kutay.MANPORT.ws;

import com.kutay.MANPORT.ws.domain.*;
import com.kutay.MANPORT.ws.dto.UserDTO;
import com.kutay.MANPORT.ws.repository.*;
import com.kutay.MANPORT.ws.service.IUserService;
import com.kutay.MANPORT.ws.util.CurrentDateCreator;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
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

    @Bean
    CommandLineRunner createInitialUsers(IUserService userService, CountryRepository countryRepository, PlantRepository plantRepository, ServerRoomRepository serverRoomRepository, BackendRepository backendRepository, FrontendRepository frontendRepository, DatabaseRepository databaseRepository, TeamRepository teamRepository, ApplicationRepository applicationRepository, JobRepository jobRepository, IssueRepository issueRepository) { //Spring projesi ayaga kalktigi zaman otomatik olarak bunun run methodu çalışır ve biz bunu başlangıç verileri eklemek için kullanıcaz.
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                if (userService.findByEmail("yamankutay1@gmail.com") == null) {
                    UserDTO user = new UserDTO();
                    user.setEmail("yamankutay1@gmail.com");
                    user.setPassword("12345678");
                    user.setName("kutay");
                    user.setSurname("yaman");
                    userService.save(user);
                }
                if (countryRepository.count() <= 0) {
                    List<Country> countryList = new ArrayList<Country>();
                    for (int i = 0; i < 10; i++) {
                        Country country = new Country();
                        country.setName("Country" + (i + 1));
                        countryList.add(country);
                    }
                    countryRepository.saveAll(countryList);
                }
                if (plantRepository.count() <= 0) {
                    List<Plant> plantList = new ArrayList<Plant>();
                    List<Country> countryList = new ArrayList<Country>();
                    countryList = countryRepository.findAll();
                    for (int i = 0; i < 10; i++) {
                        Plant plant = new Plant();
                        plant.setName("Fabrika" + (i + 1));
                        plant.setCountry(countryList.get(i));
                        plantList.add(plant);
                    }
                    plantRepository.saveAll(plantList);
                }
                if (serverRoomRepository.count() <= 0) {
                    List<ServerRoom> serverRoomList = new ArrayList<>();
                    List<Plant> plantList = plantRepository.findAll();
                    for (int i = 0; i < 10; i++) {
                        ServerRoom serverRoom = new ServerRoom();
                        serverRoom.setName("Server Room" + (i + 1));
                        Plant plant = plantList.get(i);
                        serverRoom.setPlant(plant);
                        serverRoom.setCountry(plant.getCountry());
                        serverRoomList.add(serverRoom);
                    }
                    serverRoomRepository.saveAll(serverRoomList);
                }
                if (backendRepository.count() <= 0) {
                    List<Backend> backendList = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        Backend backend = new Backend();
                        backend.setName("Backend" + (i + 1));
                        backendList.add(backend);
                    }
                    backendRepository.saveAll(backendList);
                }
                if (frontendRepository.count() <= 0) {
                    List<Frontend> frontendList = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        Frontend frontend = new Frontend();
                        frontend.setName("Frontend" + (i + 1));
                        frontendList.add(frontend);
                    }
                    frontendRepository.saveAll(frontendList);
                }
                if (databaseRepository.count() <= 0) {
                    List<Database> databaseList = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        Database database = new Database();
                        database.setName("Database" + (i + 1));
                        databaseList.add(database);
                    }
                    databaseRepository.saveAll(databaseList);
                }
                if (teamRepository.count() <= 0) {
                    List<Team> teamList = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        Team team = new Team();
                        team.setName("Team" + (i + 1));
                        teamList.add(team);
                    }
                    teamRepository.saveAll(teamList);
                }
                if (applicationRepository.count() <= 0) {
                    List<Application> applicationList = new ArrayList<>();
                    List<Backend> backendList = new ArrayList<>();
                    List<Frontend> frontendList = new ArrayList<>();
                    List<Database> databaseList = new ArrayList<>();
                    List<Team> teamList = new ArrayList<>();
                    backendList = backendRepository.findAll();
                    frontendList = frontendRepository.findAll();
                    databaseList = databaseRepository.findAll();
                    teamList = teamRepository.findAll();
                    List<Country> countryList = countryRepository.findAll();
                    List<Plant> plantList = plantRepository.findAll();
                    List<ServerRoom> serverRoomList = serverRoomRepository.findAll();

                    User user = userService.findByEmail("yamankutay1@gmail.com");
                    for (int i = 0; i < 10; i++) {
                        Application application = new Application();
                        application.setApplicationManager(user);
                        application.setBackend(backendList.get(i));
                        application.setFrontend(frontendList.get(i));
                        application.setDatabase(databaseList.get(i));
                        application.setBusinessArea(i % 3 == 0 ? BusinessAreaType.Manufacturing : BusinessAreaType.Quality);
                        application.setFullName("Application" + (i + 1));
                        application.setShortName("App" + (i + 1));
                        application.setLineCountOfBackend(100);
                        application.setLineCountOfFrontend(100);
                        application.setReleaseDate(CurrentDateCreator.currentDateAsString());
                        application.setTeam(teamList.get(i));

                        Collection<Plant> plantSetForApp = new ArrayList<>();
                        Collection<Country> countrySetForApp = new ArrayList<>();
                        Collection<ServerRoom> serverRoomsForApp = new ArrayList<>();

                        for (int j=0;j<serverRoomList.size();j++) {
                            ServerRoom serverRoom = serverRoomList.get(j);
                            long id = serverRoom.getId();
                            if (Math.floorMod(id,j+1) == 0) {
                                serverRoomsForApp.add(serverRoom);
                                plantSetForApp.add(serverRoom.getPlant());
                                countrySetForApp.add(serverRoom.getCountry());
                            }
                        }
                        application.setServerRooms(serverRoomsForApp);
                        application.setPlants(plantSetForApp);
                        application.setCountries(countrySetForApp);

                        applicationList.add(application);
                    }
                    applicationRepository.saveAll(applicationList);
                }

                if (jobRepository.count() <= 0) {
                    List<Job> jobList = new ArrayList<>();
                    List<Application> applicationList = applicationRepository.findAll();
                    for (int i = 0; i < 10; i++) {
                        Job job = new Job();
                        job.setApplication(applicationList.get(i));
                        job.setName("Job" + (i + 1));
                        jobList.add(job);
                    }
                    jobRepository.saveAll(jobList);
                }

                if (issueRepository.count() <= 0) {
                    List<Issue> issueLists = new ArrayList<>();
                    List<Job> jobList = jobRepository.findAll();
                    for (int i = 0; i < 10; i++) {
                        Issue issue = new Issue();
                        issue.setName("Issue" + (i + 1));
                        issue.setDescription("Issue" + (i + 1) + "'in sorunu var");
                        issue.setImpact("Etki" + (i + 1) + " (BU IMPACT TAM OLARAK NEYDI BAK BELKI ENUM olması gerebilir.)");
                        issue.setJob(jobList.get(i));
                        issueLists.add(issue);
                        Thread.sleep(1000);
                    }
                    issueRepository.saveAll(issueLists);
                }
            }
        };
    }
}
