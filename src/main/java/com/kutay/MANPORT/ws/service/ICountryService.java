package com.kutay.MANPORT.ws.service;

import com.kutay.MANPORT.ws.domain.Application;
import com.kutay.MANPORT.ws.dto.CountryDTO;

import java.util.List;

public interface ICountryService {
    List<CountryDTO> findAll();
    List<CountryDTO> findAllByAppAndRowStatus(Application application);
    CountryDTO findFirstById(Long id);
}
