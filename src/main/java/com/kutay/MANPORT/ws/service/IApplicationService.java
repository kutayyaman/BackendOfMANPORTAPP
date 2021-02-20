package com.kutay.MANPORT.ws.service;

import com.kutay.MANPORT.ws.domain.Application;
import com.kutay.MANPORT.ws.domain.Issue;
import com.kutay.MANPORT.ws.dto.ApplicationDropListDTO;
import com.kutay.MANPORT.ws.dto.PageableDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IApplicationService {
    List<Application> findAll();
    List<ApplicationDropListDTO> findAllAppDropListDTOByRowStatus();
    Application findFirstById(Long id);
}
