package com.kutay.MANPORT.ws.service.Impl;

import com.kutay.MANPORT.ws.domain.Application;
import com.kutay.MANPORT.ws.domain.RowStatus;
import com.kutay.MANPORT.ws.dto.JobInterfaceDTO;
import com.kutay.MANPORT.ws.repository.JobInterfaceRepository;
import com.kutay.MANPORT.ws.service.IJobInterfaceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobInterfaceServiceImpl implements IJobInterfaceService {
    private final JobInterfaceRepository jobInterfaceRepository;

    public JobInterfaceServiceImpl(JobInterfaceRepository jobInterfaceRepository) {
        this.jobInterfaceRepository = jobInterfaceRepository;
    }

    @Override
    public List<JobInterfaceDTO> findAllByApplication(Long appId) {
        return jobInterfaceRepository.findAllByRowStatusAndApplicationId(RowStatus.ACTIVE, appId);
    }
}
