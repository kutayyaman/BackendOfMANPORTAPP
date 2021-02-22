package com.kutay.MANPORT.ws.service;

import com.kutay.MANPORT.ws.domain.Country;
import com.kutay.MANPORT.ws.domain.Server;
import com.kutay.MANPORT.ws.dto.ServerDTO;

import java.util.List;

public interface IServerService {
    List<Server> findAll();
    List<Server> findAllByCountry(Country country);
    List<ServerDTO> findAllByCountryAsDTO(Country country);
    Server findFirstById(Long id);
 }
