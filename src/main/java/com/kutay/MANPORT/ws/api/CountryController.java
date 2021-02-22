package com.kutay.MANPORT.ws.api;

import com.kutay.MANPORT.ws.dto.CountryDTO;
import com.kutay.MANPORT.ws.dto.UserDTO;
import com.kutay.MANPORT.ws.service.ICountryService;
import com.kutay.MANPORT.ws.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(ApiPaths.CountryCtrl.CTRL)
@Api(value = ApiPaths.CountryCtrl.CTRL, description = "Country APIs")
public class CountryController {

    private final ICountryService countryService;

    public CountryController(ICountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping()
    @ApiOperation(value = "Get All Countries Operation", response = List.class)
    public List<CountryDTO> getAllCountries() {
        return countryService.findAll();
    }
}
