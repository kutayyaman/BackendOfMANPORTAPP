package com.kutay.MANPORT.ws.service;

import com.kutay.MANPORT.ws.domain.Application;
import com.kutay.MANPORT.ws.domain.Issue;
import com.kutay.MANPORT.ws.domain.User;
import com.kutay.MANPORT.ws.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IApplicationService {
    List<Application> findAll();

    List<ApplicationDropListDTO> findAllAppDropListDTOByRowStatus();

    Application findFirstById(Long id);

    PageableDTO<ApplicationDTOForManagementPage> findAllApplicationForManagementPageInAPage(Pageable pageable);

    ApplicationDTO changeLineStopRiskById(Long id);

    ApplicationDTO changeTrackById(Long id);

    ApplicationDTO getApplicationById(Long id);

    List<String> getBusinessAreaTypes();

    ApplicationDTO updateApplication(ApplicationDTO applicationDTO, User user);

    ApplicationDTO addApplication(ApplicationDTO applicationDTO, User user);

    SetupApplicationDTO setupAnApplicationInAServer(SetupApplicationDTO setupApplicationDTO, User currentUser);

    Application findAllByIdAndRowStatusWithApplicationServers(Long id);
}
