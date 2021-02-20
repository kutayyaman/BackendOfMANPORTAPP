package com.kutay.MANPORT.ws.api;

import com.kutay.MANPORT.ws.domain.Application;
import com.kutay.MANPORT.ws.domain.RowStatus;
import com.kutay.MANPORT.ws.dto.ApplicationDropListDTO;
import com.kutay.MANPORT.ws.dto.PageableDTO;
import com.kutay.MANPORT.ws.models.GetApplicationsSummaryModel;
import com.kutay.MANPORT.ws.repository.ApplicationRepository;
import com.kutay.MANPORT.ws.service.IApplicationService;
import com.kutay.MANPORT.ws.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
