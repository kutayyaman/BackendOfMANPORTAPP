package com.kutay.MANPORT.ws.service;

import com.kutay.MANPORT.ws.domain.Application;
import com.kutay.MANPORT.ws.domain.Issue;
import com.kutay.MANPORT.ws.domain.User;
import com.kutay.MANPORT.ws.dto.ApplicationDTO;
import com.kutay.MANPORT.ws.dto.ApplicationDTOForManagementPage;
import com.kutay.MANPORT.ws.dto.ApplicationDropListDTO;
import com.kutay.MANPORT.ws.dto.PageableDTO;
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


}
