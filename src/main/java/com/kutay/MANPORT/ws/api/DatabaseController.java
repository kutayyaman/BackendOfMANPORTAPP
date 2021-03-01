package com.kutay.MANPORT.ws.api;

import com.kutay.MANPORT.ws.dto.BackendDTO;
import com.kutay.MANPORT.ws.dto.DatabaseDTO;
import com.kutay.MANPORT.ws.service.IBackendService;
import com.kutay.MANPORT.ws.service.IDatabaseService;
import com.kutay.MANPORT.ws.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.DatabaseCtrl.CTRL)
@Api(value = ApiPaths.DatabaseCtrl.CTRL, description = "Database APIs")
public class DatabaseController {
    private final IDatabaseService databaseService;

    public DatabaseController(IDatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @GetMapping()// /api/database
    @ApiOperation(value = "Get All Database Operation", response = List.class)
    public List<DatabaseDTO> getAllDatabase() {
        return databaseService.findAll();
    }
}
