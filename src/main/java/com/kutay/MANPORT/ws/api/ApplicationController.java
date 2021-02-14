package com.kutay.MANPORT.ws.api;

import com.kutay.MANPORT.ws.dto.ApplicationNameWithOtherDataListDTO;
import com.kutay.MANPORT.ws.service.IApplicationService;
import com.kutay.MANPORT.ws.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.ApplicationCtrl.CTRL)
@Api(value = ApiPaths.ApplicationCtrl.CTRL, description = "Application APIs")
public class ApplicationController {

    private final IApplicationService applicationService;

    public ApplicationController(IApplicationService applicationService) {
        this.applicationService = applicationService;
    }


    @GetMapping() // /api/applicationsummary
    @ApiOperation(value = "List Application And Issues Operation", response = ResponseEntity.class)
    public List<ApplicationNameWithOtherDataListDTO> getApplicationWithIssues() {
        return applicationService.getApplicationsWithIssues();
    }

}
