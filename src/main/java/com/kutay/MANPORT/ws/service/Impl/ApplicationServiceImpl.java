package com.kutay.MANPORT.ws.service.Impl;

import com.kutay.MANPORT.ws.domain.*;
import com.kutay.MANPORT.ws.dto.*;
import com.kutay.MANPORT.ws.error.CloneException;
import com.kutay.MANPORT.ws.error.NotFoundException;
import com.kutay.MANPORT.ws.repository.ApplicationRepository;
import com.kutay.MANPORT.ws.repository.JobImplementsRepository;
import com.kutay.MANPORT.ws.service.*;
import com.kutay.MANPORT.ws.util.CurrentDateCreator;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ApplicationServiceImpl implements IApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ModelMapper modelMapper;
    private final IUserService userService;
    private final IServerService serverService;
    private final IApplicationCountryService applicationCountryService;
    private final IApplicationServerService applicationServerService;
    private final IJobImplementService jobImplementService;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, ModelMapper modelMapper, IUserService userService, IServerService serverService, IApplicationCountryService applicationCountryService, IApplicationServerService applicationServerService, JobImplementsRepository jobImplementsRepository, IJobImplementService jobImplementService) {
        this.applicationRepository = applicationRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.serverService = serverService;
        this.applicationCountryService = applicationCountryService;
        this.applicationServerService = applicationServerService;
        this.jobImplementService = jobImplementService;
    }

    @Override
    public List<Application> findAll() {
        return applicationRepository.findAllByRowStatus(RowStatus.ACTIVE);
    }

    @Override
    public List<ApplicationDropListDTO> findAllAppDropListDTOByRowStatus() {
        return applicationRepository.findAllAppDropListDTOByRowStatus(RowStatus.ACTIVE);
    }

    @Override
    public Application findFirstById(Long id) {
        return applicationRepository.findFirstById(id);
    }

    @Override
    public PageableDTO<ApplicationDTOForManagementPage> findAllApplicationForManagementPageInAPage(Pageable pageable) {
        PageableDTO<ApplicationDTOForManagementPage> result = new PageableDTO<>();
        List<ApplicationDTOForManagementPage> dtoList = new ArrayList<>();

        Page<Application> pageFromRepository = applicationRepository.findAllByRowStatus(RowStatus.ACTIVE, pageable);
        result.setNumber(pageFromRepository.getNumber());
        result.setLast(pageFromRepository.isLast());
        result.setFirst(pageFromRepository.isFirst());

        List<Application> applicationList = pageFromRepository.getContent();
        for (Application application : applicationList) {
            ApplicationDTOForManagementPage dto = new ApplicationDTOForManagementPage(application);
            dtoList.add(dto);
        }
        result.setContent(dtoList);

        return result;
    }

    @Override
    public ApplicationDTO changeLineStopRiskById(Long id) {
        Application application = applicationRepository.findFirstByIdAndRowStatus(id, RowStatus.ACTIVE);
        application.setLineStopRisk(!application.isLineStopRisk());
        applicationRepository.save(application);
        ApplicationDTO applicationDTO = new ApplicationDTO(application);

        return applicationDTO;
    }

    @Override
    public ApplicationDTO changeTrackById(Long id) {
        Application application = applicationRepository.findFirstByIdAndRowStatus(id, RowStatus.ACTIVE);
        application.setTrack(!application.isTrack());
        applicationRepository.save(application);
        ApplicationDTO applicationDTO = new ApplicationDTO(application);
        return applicationDTO;
    }

    @Override
    public ApplicationDTO getApplicationById(Long id) {
        Application application = applicationRepository.findFirstByIdAndRowStatus(id, RowStatus.ACTIVE);
        ApplicationDTO applicationDTO = new ApplicationDTO(application);
        return applicationDTO;
    }

    @Override
    public List<String> getBusinessAreaTypes() {
        List<String> businessAreaTypesAsString = new ArrayList<>();
        List<BusinessAreaType> businessAreaTypes = Arrays.asList(BusinessAreaType.values());
        for (BusinessAreaType businessAreaType : businessAreaTypes) {
            businessAreaTypesAsString.add(businessAreaType.toString());
        }
        return businessAreaTypesAsString;
    }

    @Override
    public ApplicationDTO updateApplication(ApplicationDTO applicationDTO, User currentUser) {
        Application application = applicationRepository.findFirstByIdAndRowStatus(applicationDTO.getId(), RowStatus.ACTIVE);
        if (application == null) {
            throw new NotFoundException();
        }
        application.setModifiedDate(CurrentDateCreator.currentDateAsString());
        if (currentUser != null) {
            application.setModifiedBy(currentUser.getEmail());
        }
        return saveApplication(applicationDTO, currentUser, application);
    }

    @Override
    public ApplicationDTO addApplication(ApplicationDTO applicationDTO, User currentUser) {
        Application newApplication = new Application();
        newApplication.setCreatedDate(CurrentDateCreator.currentDateAsString());
        if (currentUser != null) {
            newApplication.setCreatedBy(currentUser.getEmail());
        }
        return saveApplication(applicationDTO, currentUser, newApplication);
    }

    @Override
    public SetupApplicationDTO setupAnApplicationInAServer(SetupApplicationDTO setupApplicationDTO, User currentUser) {
        Application application = applicationRepository.findFirstByIdAndRowStatus(setupApplicationDTO.getAppId(), RowStatus.ACTIVE);
        Server server = serverService.findFirstById(setupApplicationDTO.getServerId());

        List<JobImplement> jobImplements = new ArrayList<>();

        for (JobInterface jobInterface : application.getJobInterfaces()) {
            JobImplement jobImplement = new JobImplement();
            jobImplement.setJobInterface(jobInterface);
            jobImplement.setServer(server);
            jobImplements.add(jobImplement);
        }

        ApplicationServer applicationServer = new ApplicationServer();
        applicationServer.setApplication(application);
        applicationServer.setServer(server);

        Country country = server.getCountry();

        ApplicationCountry applicationCountry = applicationCountryService.findFirstByApplicationAndCountryAndRowStatus(application, country, RowStatus.ACTIVE);
        if (applicationCountry == null) {
            applicationCountry = new ApplicationCountry();
            applicationCountry.setApplication(application);
            applicationCountry.setCountry(country);
            applicationCountry.setCount(1);
        } else {
            applicationCountry.setCount(applicationCountry.getCount() + 1);
        }

        applicationCountryService.save(applicationCountry);
        applicationServerService.save(applicationServer);
        jobImplementService.saveAll(jobImplements);

        return setupApplicationDTO;
    }

    private ApplicationDTO saveApplication(ApplicationDTO applicationDTO, User currentUser, Application newApplication) {
        try {
            newApplication = setApplicationDTOToApplication(applicationDTO, newApplication);
        } catch (CloneNotSupportedException e) {
            throw new CloneException();
        }

        applicationRepository.save(newApplication);
        return applicationDTO;
    }

    private Application setApplicationDTOToApplication(ApplicationDTO applicationDTO, Application applicationParam) throws CloneNotSupportedException {
        Application application = (Application) applicationParam.clone();
        application.setId(applicationDTO.getId());
        application.setShortName(applicationDTO.getShortName());
        application.setFullName(applicationDTO.getFullName());
        application.setLineStopRisk(applicationDTO.getLineStopRisk());
        if (applicationDTO.getTrack() != null) {
            application.setTrack(applicationDTO.getTrack());
        }
        application.setReleaseDate(applicationDTO.getReleaseDate());
        application.setLineOfBackendCode(applicationDTO.getLineOfBackendCode());
        application.setLineOfFrontendCode(applicationDTO.getLineOfFrontendCode());


        User user = userService.findByIdAndTeamId(applicationDTO.getResponsibleUserId(), applicationDTO.getResponsibleTeamId()); //team id'sine gorede aradim cunku bu istek belki postman gibi bir aracla atilacak ve team idsini kafasina gore verecek ozaman sorun olmasin diye.
        if (user == null) {
            throw new NotFoundException();
        }
        application.setUser(user);

        application.setBusinessAreaType(BusinessAreaType.valueOf(applicationDTO.getBusinessAreaType()));

        return application;
    }
}
