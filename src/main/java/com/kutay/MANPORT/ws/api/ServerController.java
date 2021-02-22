package com.kutay.MANPORT.ws.api;

import com.kutay.MANPORT.ws.domain.Country;
import com.kutay.MANPORT.ws.domain.Server;
import com.kutay.MANPORT.ws.dto.ServerDTO;
import com.kutay.MANPORT.ws.service.ICountryService;
import com.kutay.MANPORT.ws.service.IServerService;
import com.kutay.MANPORT.ws.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(ApiPaths.ServerCtrl.CTRL) // /api/server
@Api(value = ApiPaths.ServerCtrl.CTRL, description = "Server APIs")
public class ServerController {
    private final IServerService serverService;
    private final ICountryService countryService;

    public ServerController(IServerService serverService, ICountryService countryService) {
        this.serverService = serverService;
        this.countryService = countryService;
    }

    @GetMapping("/{countryId}") // /api/server/{countryId}
    @ApiOperation(value = "Get Servers By CountryId", response = List.class)
    public List<ServerDTO> getServersByCountryId(@PathVariable() Long countryId) {
        Country country= new Country();
        country.setId(countryId);
        return serverService.findAllByCountryAsDTO(country);
    }
}
