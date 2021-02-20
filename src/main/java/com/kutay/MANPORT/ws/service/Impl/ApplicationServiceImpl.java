package com.kutay.MANPORT.ws.service.Impl;

import com.kutay.MANPORT.ws.domain.Application;
import com.kutay.MANPORT.ws.domain.RowStatus;
import com.kutay.MANPORT.ws.dto.ApplicationDropListDTO;
import com.kutay.MANPORT.ws.dto.PageableDTO;
import com.kutay.MANPORT.ws.repository.ApplicationRepository;
import com.kutay.MANPORT.ws.service.IApplicationService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImpl implements IApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ModelMapper modelMapper;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, ModelMapper modelMapper) {
        this.applicationRepository = applicationRepository;
        this.modelMapper = modelMapper;
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
}
