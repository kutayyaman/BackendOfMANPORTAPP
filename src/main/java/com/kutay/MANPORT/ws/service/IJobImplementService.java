package com.kutay.MANPORT.ws.service;

import com.kutay.MANPORT.ws.domain.JobImplement;

import java.util.List;

public interface IJobImplementService {
    List<JobImplement> saveAll(List<JobImplement> jobImplements);
}
