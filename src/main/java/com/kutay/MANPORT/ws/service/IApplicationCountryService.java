package com.kutay.MANPORT.ws.service;

import com.kutay.MANPORT.ws.domain.Application;
import com.kutay.MANPORT.ws.domain.ApplicationCountry;
import com.kutay.MANPORT.ws.domain.Country;
import com.kutay.MANPORT.ws.domain.RowStatus;
import com.kutay.MANPORT.ws.dto.ApplicationCountryDTO;
import com.kutay.MANPORT.ws.dto.ManagementFactoryDTO;

import java.util.List;

public interface IApplicationCountryService {
    List<ApplicationCountry> findAllByApplication(Application application);

    ApplicationCountry findFirstByApplicationAndCountry(Application application, Country country);

    List<ManagementFactoryDTO> getManagementFactoriesByAppId(Long appId);

    ApplicationCountryDTO changeAliveByAppIdAndCountryId(Long appId, Long countryId);

    ApplicationCountryDTO changeTrackByAppIdAndCountryId(Long appId, Long countryId);

    ApplicationCountryDTO findFirstByApplicationAndCountryAndRowStatusRETURNDTO(Application application, Country country, RowStatus rowStatus);

    ApplicationCountry findFirstByApplicationAndCountryAndRowStatus(Application application, Country country, RowStatus rowStatus);

    ApplicationCountry save(ApplicationCountry applicationCountry);

}
