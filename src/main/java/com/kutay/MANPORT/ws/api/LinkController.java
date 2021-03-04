package com.kutay.MANPORT.ws.api;

import com.kutay.MANPORT.ws.service.ILinkService;
import com.kutay.MANPORT.ws.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.LinkCtrl.CTRL) // /api/link
@Api(value = ApiPaths.LinkCtrl.CTRL, description = "Link APIs")
public class LinkController {

    private final ILinkService linkService;

    public LinkController(ILinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/{appId}") // /api/link/{appId}
    @ApiOperation(value = "Get Links By appId", response = List.class)
    public List<?> getJobInterfaceByAppId(@PathVariable Long appId) {
        return linkService.getAllLinksSortedForManagementPageByAppId(appId);
    }
}
