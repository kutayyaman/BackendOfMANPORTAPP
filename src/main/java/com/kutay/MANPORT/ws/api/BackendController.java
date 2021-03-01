package com.kutay.MANPORT.ws.api;

import com.kutay.MANPORT.ws.MyAnnotations.CurrentUser;
import com.kutay.MANPORT.ws.domain.User;
import com.kutay.MANPORT.ws.dto.BackendDTO;
import com.kutay.MANPORT.ws.service.IBackendService;
import com.kutay.MANPORT.ws.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.BackendCtrl.CTRL) // /api/backend
@Api(value = ApiPaths.BackendCtrl.CTRL, description = "Backend APIs")
public class BackendController {
    private final IBackendService backendService;

    public BackendController(IBackendService backendService) {
        this.backendService = backendService;
    }

    @GetMapping()// /api/backend
    @ApiOperation(value = "Get All Backend Operation", response = List.class)
    public List<BackendDTO> getAllBackend() {
        return backendService.findAll();
    }
}
