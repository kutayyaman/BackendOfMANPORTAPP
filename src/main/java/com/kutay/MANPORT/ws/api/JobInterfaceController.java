package com.kutay.MANPORT.ws.api;

import com.kutay.MANPORT.ws.MyAnnotations.CurrentUser;
import com.kutay.MANPORT.ws.domain.User;
import com.kutay.MANPORT.ws.dto.AddAJobInterfaceToAnApplicationDTO;
import com.kutay.MANPORT.ws.dto.IssueDTO;
import com.kutay.MANPORT.ws.dto.JobInterfaceDTO;
import com.kutay.MANPORT.ws.dto.SetupApplicationDTO;
import com.kutay.MANPORT.ws.service.IJobInterfaceService;
import com.kutay.MANPORT.ws.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @ApiOperation(value = "Get JobInterface By appId", response = List.class)
    public List<JobInterfaceDTO> getJobInterfaceByAppId(@PathVariable Long appId) {
        return jobInterfaceService.findAllByApplication(appId);
    }

    @PostMapping() // /api/jobInterface
    @ApiOperation(value = " addAJobInterfaceToAnApplication Operation", response = JobInterfaceDTO.class)
    public AddAJobInterfaceToAnApplicationDTO addAJobInterfaceToAnApplication(@Valid @RequestBody(required = true) AddAJobInterfaceToAnApplicationDTO dto, @CurrentUser User currentUser) {
        return jobInterfaceService.addAJobInterfaceToAnApplication(dto, currentUser);
    }
}
