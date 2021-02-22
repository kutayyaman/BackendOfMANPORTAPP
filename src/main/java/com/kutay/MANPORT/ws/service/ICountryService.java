package com.kutay.MANPORT.ws.service;

import com.kutay.MANPORT.ws.dto.CountryDTO;

import java.util.List;

public interface ICountryService {
    List<CountryDTO> findAll();
}
