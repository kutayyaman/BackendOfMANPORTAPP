package com.kutay.MANPORT.ws.api;

import com.kutay.MANPORT.ws.dto.BackendDTO;
import com.kutay.MANPORT.ws.dto.FrontendDTO;
import com.kutay.MANPORT.ws.service.IFrontendService;
import com.kutay.MANPORT.ws.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.FrontendCtrl.CTRL) // /api/frontend
@Api(value = ApiPaths.FrontendCtrl.CTRL, description = "Frontend APIs")
public class FrontendController {
    private final IFrontendService frontendService;

    public FrontendController(IFrontendService frontendService) {
        this.frontendService = frontendService;
    }

    @GetMapping()// /api/frontend
    @ApiOperation(value = "Get All Frontend Operation", response = List.class)
    public List<FrontendDTO> getAllFrontend() {
        return frontendService.findAll();
    }
}
