package com.kutay.MANPORT.ws.service.Impl;

import com.kutay.MANPORT.ws.domain.*;
import com.kutay.MANPORT.ws.dto.AddAJobInterfaceToAnApplicationDTO;
import com.kutay.MANPORT.ws.dto.JobInterfaceDTO;
import com.kutay.MANPORT.ws.error.NotFoundException;
import com.kutay.MANPORT.ws.repository.JobInterfaceRepository;
import com.kutay.MANPORT.ws.service.IApplicationService;
import com.kutay.MANPORT.ws.service.IJobImplementService;
import com.kutay.MANPORT.ws.service.IJobInterfaceService;
import com.kutay.MANPORT.ws.service.IServerService;
import com.kutay.MANPORT.ws.util.CurrentDateCreator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobInterfaceServiceImpl implements IJobInterfaceService {
    private final JobInterfaceRepository jobInterfaceRepository;
    private final IApplicationService applicationService;
    private final IServerService serverService;
    private final IJobImplementService jobImplementService;

    public JobInterfaceServiceImpl(JobInterfaceRepository jobInterfaceRepository, IApplicationService applicationService, IServerService serverService, IJobImplementService jobImplementService) {
        this.jobInterfaceRepository = jobInterfaceRepository;
        this.applicationService = applicationService;
        this.serverService = serverService;
        this.jobImplementService = jobImplementService;
    }

    @Override
    public List<JobInterfaceDTO> findAllByApplication(Long appId) {
        return jobInterfaceRepository.findAllByRowStatusAndApplicationId(RowStatus.ACTIVE, appId);
    }

    @Override
    public AddAJobInterfaceToAnApplicationDTO addAJobInterfaceToAnApplication(AddAJobInterfaceToAnApplicationDTO dto, User user) {
        Application application = applicationService.findAllByIdAndRowStatusWithApplicationServers(dto.getAppId());
        if (application == null) {
            throw new NotFoundException();
        }

        JobInterface jobInterface = new JobInterface();
        jobInterface.setApplication(application);
        jobInterface.setName(dto.getJobInterfaceName());
        jobInterface.setCreatedDate(CurrentDateCreator.currentDateAsDate());
        if (user != null) {
            jobInterface.setCreatedBy(user.getEmail());
        }

        jobInterface = jobInterfaceRepository.save(jobInterface);

        createJobImplementsOfJobInterface(application, jobInterface);

        return dto;
    }

    private void createJobImplementsOfJobInterface(Application application, JobInterface jobInterface) {
        List<ApplicationServer> applicationServers = application.getApplicationServers(); //burdan serverlari cekebiliriz.

        List<JobImplement> jobImplements = new ArrayList<>();
        for (ApplicationServer applicationServer : applicationServers) {
            Server server = applicationServer.getServer();
            JobImplement jobImplement = new JobImplement();
            jobImplement.setServer(server);
            jobImplement.setJobInterface(jobInterface);
            jobImplements.add(jobImplement);
        }

        jobImplementService.saveAll(jobImplements);
    }
}
