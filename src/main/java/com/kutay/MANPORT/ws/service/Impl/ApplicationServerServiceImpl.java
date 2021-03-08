package com.kutay.MANPORT.ws.service.Impl;

import com.kutay.MANPORT.ws.domain.ApplicationServer;
import com.kutay.MANPORT.ws.repository.ApplicationServerRepository;
import com.kutay.MANPORT.ws.service.IApplicationServerService;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServerServiceImpl implements IApplicationServerService {
    private final ApplicationServerRepository applicationServerRepository;

    public ApplicationServerServiceImpl(ApplicationServerRepository applicationServerRepository) {
        this.applicationServerRepository = applicationServerRepository;
    }

    @Override
    public ApplicationServer save(ApplicationServer applicationServer) {
        return applicationServerRepository.save(applicationServer);
    }
}
