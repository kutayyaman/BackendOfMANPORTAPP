package com.kutay.MANPORT.ws.service.Impl;

import com.kutay.MANPORT.ws.domain.JobImplement;
import com.kutay.MANPORT.ws.domain.RowStatus;
import com.kutay.MANPORT.ws.repository.JobImplementsRepository;
import com.kutay.MANPORT.ws.service.IJobImplementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobImplementServiceImpl implements IJobImplementService {
    private final JobImplementsRepository jobImplementsRepository;

    public JobImplementServiceImpl(JobImplementsRepository jobImplementsRepository) {
        this.jobImplementsRepository = jobImplementsRepository;
    }

    @Override
    public List<JobImplement> saveAll(List<JobImplement> jobImplements) {
        return jobImplementsRepository.saveAll(jobImplements);
    }

    @Override
    public JobImplement findFirstById(Long id) {
        return jobImplementsRepository.findFirstByIdAndRowStatus(id, RowStatus.ACTIVE);
    }
}
