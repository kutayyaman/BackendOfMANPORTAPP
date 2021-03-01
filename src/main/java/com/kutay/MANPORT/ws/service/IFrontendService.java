package com.kutay.MANPORT.ws.service;


import com.kutay.MANPORT.ws.domain.Frontend;
import com.kutay.MANPORT.ws.dto.FrontendDTO;

import java.util.List;

public interface IFrontendService {
    List<FrontendDTO> findAll();
    Frontend findById(Long id);
}
