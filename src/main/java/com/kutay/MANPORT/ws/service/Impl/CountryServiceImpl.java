package com.kutay.MANPORT.ws.service.Impl;

import com.kutay.MANPORT.ws.domain.Application;
import com.kutay.MANPORT.ws.domain.Country;
import com.kutay.MANPORT.ws.domain.RowStatus;
import com.kutay.MANPORT.ws.dto.CountryDTO;
import com.kutay.MANPORT.ws.error.NotFoundException;
import com.kutay.MANPORT.ws.repository.CountryRepository;
import com.kutay.MANPORT.ws.service.ICountryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryServiceImpl implements ICountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<CountryDTO> findAll() {
        List<Country> countryList = countryRepository.findAllByRowStatus(RowStatus.ACTIVE);
        List<CountryDTO> result = new ArrayList<>();

        for (Country country : countryList) {
            CountryDTO countryDTO = new CountryDTO(country);
            result.add(countryDTO);
        }

        return result;
    }

    @Override
    public List<CountryDTO> findAllByAppAndRowStatus(Application application) {
        List<Country> countryList = countryRepository.findAllByAppAndRowStatus(application, RowStatus.ACTIVE);
        List<CountryDTO> countryDTOS = new ArrayList<>();

        for (Country country : countryList) {
            CountryDTO countryDTO = new CountryDTO(country);
            countryDTOS.add(countryDTO);
        }

        return countryDTOS;
    }

    @Override
    public CountryDTO findFirstById(Long id) {
        Country country = countryRepository.findFirstByIdAndRowStatus(id, RowStatus.ACTIVE);
        if (country == null) {
            throw new NotFoundException();
        }
        CountryDTO countryDTO = new CountryDTO(country);
        return countryDTO;
    }
}
