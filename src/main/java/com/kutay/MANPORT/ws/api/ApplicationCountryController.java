package com.kutay.MANPORT.ws.api;

import com.kutay.MANPORT.ws.dto.*;
import com.kutay.MANPORT.ws.service.IApplicationCountryService;
import com.kutay.MANPORT.ws.service.IApplicationService;
import com.kutay.MANPORT.ws.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.ApplicationCountryCtrl.CTRL) // /api/applicationCountry
@Api(value = ApiPaths.ApplicationCountryCtrl.CTRL, description = "ApplicationCountry APIs")
public class ApplicationCountryController {

    private final IApplicationService applicationService;
    private final IApplicationCountryService applicationCountryService;

    public ApplicationCountryController(IApplicationService applicationService, IApplicationCountryService applicationCountryService) {
        this.applicationService = applicationService;
        this.applicationCountryService = applicationCountryService;
    }

    @GetMapping("/getManagementFactoriesByAppId")// /api/applicationCountry/getManagementFactoriesByAppId?appId=41
    @ApiOperation(value = "Get All ManagementFactoryDTO By AppId Operation", response = List.class)
    public List<ManagementFactoryDTO> findAllManagementFactoryDTOByAppAndRowStatus(@RequestParam Long appId) {
        return applicationCountryService.getManagementFactoriesByAppId(appId); //appId mevcut mu diye bu kullanilan method icinde kontrol yapiliyor ondan burda yapmadim
    }

    @PutMapping("/changeAlive")// /api/applicationCountry/changeAlive
    @ApiOperation(value = "change alive by appId and countryId Operation", response = ApplicationCountryDTO.class)
    public ApplicationCountryDTO changeAliveByAppIdAndCountryId(@RequestBody(required = true) ChangeAliveOrTrackDTO changeAliveOrTrackDTO) {
        return applicationCountryService.changeAliveByAppIdAndCountryId(changeAliveOrTrackDTO.getAppId(), changeAliveOrTrackDTO.getCountryId());
    }

    @PutMapping("/changeTrack")// /api/applicationCountry/changeTrack
    @ApiOperation(value = "change Track by appId and countryId Operation", response = ApplicationCountryDTO.class)
    public ApplicationCountryDTO changeTrackByAppIdAndCountryId(@RequestBody(required = true) ChangeAliveOrTrackDTO changeAliveOrTrackDTO) {
        return applicationCountryService.changeTrackByAppIdAndCountryId(changeAliveOrTrackDTO.getAppId(), changeAliveOrTrackDTO.getCountryId());
    }
}
