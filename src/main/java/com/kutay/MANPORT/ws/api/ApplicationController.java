package com.kutay.MANPORT.ws.api;

import com.kutay.MANPORT.ws.MyAnnotations.CurrentUser;
import com.kutay.MANPORT.ws.domain.Application;
import com.kutay.MANPORT.ws.domain.BusinessAreaType;
import com.kutay.MANPORT.ws.domain.RowStatus;
import com.kutay.MANPORT.ws.domain.User;
import com.kutay.MANPORT.ws.dto.*;
import com.kutay.MANPORT.ws.models.GetApplicationsSummaryModel;
import com.kutay.MANPORT.ws.repository.ApplicationRepository;
import com.kutay.MANPORT.ws.service.IApplicationService;
import com.kutay.MANPORT.ws.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(ApiPaths.ApplicationCtrl.CTRL)  // /api/application
@Api(value = ApiPaths.ApplicationCtrl.CTRL, description = "Application APIs")
public class ApplicationController {

    private final IApplicationService applicationService;

    public ApplicationController(IApplicationService applicationService) {
        this.applicationService = applicationService;
    }


    @GetMapping("/droplist")
    @ApiOperation(value = "Get Applications Drop List DTO Operation", response = GetApplicationsSummaryModel.class)
    public List<ApplicationDropListDTO> findAllAppDropListDTOByRowStatus() {
        return applicationService.findAllAppDropListDTOByRowStatus();
    }

    @GetMapping("/applicationForManagementPage")
    @ApiOperation(value = "Get Applications DTO For Management Page Operation", response = PageableDTO.class)
    public PageableDTO<ApplicationDTOForManagementPage> findAllApplicationsForManagementPage(Pageable pageable) {
        return applicationService.findAllApplicationForManagementPageInAPage(pageable);
    }

    // /api/application/changeLineStopRisk/{id}
    @PostMapping("/changeLineStopRisk/{id}")
    @ApiOperation(value = "Change Line Stop Risk By Id Operation", response = ApplicationDTO.class)
    public ApplicationDTO changeLineStopRiskById(@PathVariable Long id) {
        return applicationService.changeLineStopRiskById(id);
    }

    // /api/application/changeTrack/{id}
    @PostMapping("/changeTrack/{id}")
    @ApiOperation(value = "Change Track By Id Operation", response = ApplicationDTO.class)
    public ApplicationDTO changeTrackById(@PathVariable Long id) {
        return applicationService.changeTrackById(id);
    }

    // /api/application/{id}
    @GetMapping("/{id}")
    @ApiOperation(value = "Get Application By Id", response = ApplicationDTO.class)
    public ApplicationDTO getApplicationById(@PathVariable Long id) {
        return applicationService.getApplicationById(id);
    }

    // /api/application/businessAreaTypes
    @GetMapping("/businessAreaTypes")
    @ApiOperation(value = "Get BusinessAreaTypes", response = List.class)
    public List<String> getBusinessAreaTypes() {
        return applicationService.getBusinessAreaTypes();
    }

    // /api/application
    @PutMapping()
    @ApiOperation(value = "Update Application Operation", response = ApplicationDTO.class)
    public ApplicationDTO updateApplication(@RequestBody(required = true) ApplicationDTO applicationDTO, @CurrentUser User currentUser) {
        return applicationService.updateApplication(applicationDTO, currentUser);
    }

    // /api/application
    @PostMapping()
    @ApiOperation(value = "Add Application Operation", response = ApplicationDTO.class)
    public ApplicationDTO addApplication(@Valid @RequestBody(required = true) ApplicationDTO applicationDTO, @CurrentUser User currentUser) {
        return applicationService.addApplication(applicationDTO, currentUser);
    }

    // /api/application/setup
    @PostMapping("/setup")
    @ApiOperation(value = "Setup An Application In A Server", response = SetupApplicationDTO.class)
    public SetupApplicationDTO setupAnApplicationInAServer(@Valid @RequestBody(required = true) SetupApplicationDTO setupApplicationDTO, @CurrentUser User currentUser) {
        return applicationService.setupAnApplicationInAServer(setupApplicationDTO, currentUser);
    }
}
