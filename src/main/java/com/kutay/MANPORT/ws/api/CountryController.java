package com.kutay.MANPORT.ws.api;

import com.kutay.MANPORT.ws.domain.Application;
import com.kutay.MANPORT.ws.domain.Country;
import com.kutay.MANPORT.ws.dto.CountryDTO;
import com.kutay.MANPORT.ws.error.NotFoundException;
import com.kutay.MANPORT.ws.service.IApplicationService;
import com.kutay.MANPORT.ws.service.ICountryService;
import com.kutay.MANPORT.ws.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.CountryCtrl.CTRL)
@Api(value = ApiPaths.CountryCtrl.CTRL, description = "Country APIs")
public class CountryController {

    private final ICountryService countryService;
    private final IApplicationService applicationService;

    public CountryController(ICountryService countryService, IApplicationService applicationService) {
        this.countryService = countryService;
        this.applicationService = applicationService;
    }

    @GetMapping()
    @ApiOperation(value = "Get All Countries Operation", response = List.class)
    public List<CountryDTO> getAllCountries() {
        return countryService.findAll();
    }

    @GetMapping("/getByAppId")// /api/country/getByAppId?appId=41
    @ApiOperation(value = "Get All Countries Operation", response = List.class)
    public List<CountryDTO> findAllByAppAndRowStatus(@RequestParam Long appId){
        Application application = applicationService.findFirstById(appId);
        if(application == null){
            throw new NotFoundException();
        }
        return countryService.findAllByAppAndRowStatus(application);
    }
}
