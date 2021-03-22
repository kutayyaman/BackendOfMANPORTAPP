package com.kutay.MANPORT.ws.service;

import com.kutay.MANPORT.ws.domain.JobImplement;
import com.kutay.MANPORT.ws.domain.RowStatus;

import java.util.List;

public interface IJobImplementService {
    List<JobImplement> saveAll(List<JobImplement> jobImplements);

    JobImplement findFirstById(Long id);
}
