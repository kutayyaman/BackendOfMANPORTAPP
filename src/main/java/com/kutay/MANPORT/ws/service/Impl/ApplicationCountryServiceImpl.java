package com.kutay.MANPORT.ws.service.Impl;

import com.kutay.MANPORT.ws.domain.Application;
import com.kutay.MANPORT.ws.domain.ApplicationCountry;
import com.kutay.MANPORT.ws.domain.Country;
import com.kutay.MANPORT.ws.domain.RowStatus;
import com.kutay.MANPORT.ws.dto.ApplicationCountryDTO;
import com.kutay.MANPORT.ws.dto.CountryDTO;
import com.kutay.MANPORT.ws.dto.ManagementFactoryDTO;
import com.kutay.MANPORT.ws.error.NotFoundException;
import com.kutay.MANPORT.ws.repository.ApplicationCountryRepository;
import com.kutay.MANPORT.ws.service.IApplicationCountryService;
import com.kutay.MANPORT.ws.service.IApplicationService;
import com.kutay.MANPORT.ws.service.ICountryService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationCountryServiceImpl implements IApplicationCountryService {
    private final ApplicationCountryRepository applicationCountryRepository;
    private final IApplicationService applicationService;
    private final ICountryService countryService;

    public ApplicationCountryServiceImpl(ApplicationCountryRepository applicationCountryRepository, @Lazy IApplicationService applicationService, ICountryService countryService) {
        this.applicationCountryRepository = applicationCountryRepository;
        this.applicationService = applicationService;
        this.countryService = countryService;
    }

    @Override
    public List<ApplicationCountry> findAllByApplication(Application application) {
        return applicationCountryRepository.findAllByApplicationAndRowStatus(application, RowStatus.ACTIVE);
    }

    @Override
    public ApplicationCountry findFirstByApplicationAndCountry(Application application, Country country) {
        return applicationCountryRepository.findFirstByApplicationAndCountryAndRowStatus(application, country, RowStatus.ACTIVE);
    }

    @Override
    public List<ManagementFactoryDTO> getManagementFactoriesByAppId(Long appId) {
        List<ManagementFactoryDTO> result = new ArrayList<>();

        Application application = applicationService.findFirstById(appId);
        if (application == null) {
            throw new NotFoundException();
        }

        List<ApplicationCountry> applicationCountries = findAllByApplication(application);
        for (ApplicationCountry applicationCountry : applicationCountries) {
            ManagementFactoryDTO managementFactoryDTO = new ManagementFactoryDTO();

            Country country = applicationCountry.getCountry();
            CountryDTO countryDTO = new CountryDTO(country);

            managementFactoryDTO.setCountryDTO(countryDTO);
            managementFactoryDTO.setAlive(applicationCountry.isAlive());
            managementFactoryDTO.setTrack(applicationCountry.isTrack());

            result.add(managementFactoryDTO);
        }

        return result;
    }

    @Override
    public ApplicationCountryDTO changeAliveByAppIdAndCountryId(Long appId, Long countryId) {
        ApplicationCountry applicationCountry = getApplicationCountry(appId, countryId);
        applicationCountry.setAlive(!applicationCountry.isAlive());
        applicationCountryRepository.save(applicationCountry);

        ApplicationCountryDTO applicationCountryDTO = new ApplicationCountryDTO(applicationCountry);
        return applicationCountryDTO;
    }

    @Override
    public ApplicationCountryDTO changeTrackByAppIdAndCountryId(Long appId, Long countryId) {
        ApplicationCountry applicationCountry = getApplicationCountry(appId, countryId);
        applicationCountry.setTrack(!applicationCountry.isTrack());
        applicationCountryRepository.save(applicationCountry);

        ApplicationCountryDTO applicationCountryDTO = new ApplicationCountryDTO(applicationCountry);
        return applicationCountryDTO;
    }

    @Override
    public ApplicationCountryDTO findFirstByApplicationAndCountryAndRowStatusRETURNDTO(Application application, Country country, RowStatus rowStatus) {
        ApplicationCountry applicationCountry = applicationCountryRepository.findFirstByApplicationAndCountryAndRowStatus(application, country, RowStatus.ACTIVE);

        ApplicationCountryDTO applicationCountryDTO = new ApplicationCountryDTO(applicationCountry);
        return applicationCountryDTO;
    }

    @Override
    public ApplicationCountry findFirstByApplicationAndCountryAndRowStatus(Application application, Country country, RowStatus rowStatus) {
        return applicationCountryRepository.findFirstByApplicationAndCountryAndRowStatus(application, country, RowStatus.ACTIVE);
    }

    @Override
    public ApplicationCountry save(ApplicationCountry applicationCountry) {
        return applicationCountryRepository.save(applicationCountry);
    }

    private ApplicationCountry getApplicationCountry(Long appId, Long countryId) {
        Application application = applicationService.findFirstById(appId);
        CountryDTO countryDTO = countryService.findFirstById(countryId);
        if (application == null || countryDTO == null) {
            throw new NotFoundException();
        }
        Country country = new Country();
        country.setId(countryDTO.getId());

        return applicationCountryRepository.findFirstByApplicationAndCountryAndRowStatus(application, country, RowStatus.ACTIVE);
    }
}
