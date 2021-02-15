package com.kutay.MANPORT.ws.service;

import com.kutay.MANPORT.ws.domain.Country;
import com.kutay.MANPORT.ws.domain.Server;

import java.util.List;

public interface IServerService {
    List<Server> findAll();
    List<Server> findAllByCountry(Country country);
 }
