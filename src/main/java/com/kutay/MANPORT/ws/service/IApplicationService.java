package com.kutay.MANPORT.ws.service;

import com.kutay.MANPORT.ws.domain.Application;

import java.util.List;

public interface IApplicationService {
    List<Application> findAll();
}
