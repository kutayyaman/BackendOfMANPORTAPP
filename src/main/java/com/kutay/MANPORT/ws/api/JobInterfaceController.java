package com.kutay.MANPORT.ws.api;

import com.kutay.MANPORT.ws.dto.IssueDTO;
import com.kutay.MANPORT.ws.dto.JobInterfaceDTO;
import com.kutay.MANPORT.ws.service.IJobInterfaceService;
import com.kutay.MANPORT.ws.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.JobInterfaceCtrl.CTRL) // /api/jobInterface
@Api(value = ApiPaths.IssueCtrl.CTRL, description = "Issue APIs")
public class JobInterfaceController {

    private final IJobInterfaceService jobInterfaceService;

    public JobInterfaceController(IJobInterfaceService jobInterfaceService) {
        this.jobInterfaceService = jobInterfaceService;
    }

    @GetMapping("/{appId}") // /api/jobInterface/{appId}
    @ApiOperation(value = "Get JobInterface By appId", response = IssueDTO.class)
    public List<JobInterfaceDTO> getJobInterfaceByAppId(@PathVariable Long appId) {
        return jobInterfaceService.findAllByApplication(appId);
    }
}
