package com.kutay.MANPORT.ws.api;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.kutay.MANPORT.ws.MyAnnotations.CurrentUser;
import com.kutay.MANPORT.ws.domain.ImpactType;
import com.kutay.MANPORT.ws.domain.User;
import com.kutay.MANPORT.ws.dto.*;
import com.kutay.MANPORT.ws.error.JobDoesntExistInServerException;
import com.kutay.MANPORT.ws.service.IIssueService;
import com.kutay.MANPORT.ws.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(ApiPaths.IssueCtrl.CTRL)
@Api(value = ApiPaths.IssueCtrl.CTRL, description = "Issue APIs")
public class IssueController {

    private final IIssueService issueService;
    private final MessageSource messageSource;

    public IssueController(IIssueService issueService, MessageSource messageSource) {
        this.issueService = issueService;
        this.messageSource = messageSource;
    }


    @GetMapping("/top3") // "/api/issue/top3"
    @ApiOperation(value = "Find Top 3 Issues", response = List.class)
    public List<TopIssueDTO> findTop3Issues() {
        List<TopIssueDTO> top3TopIssueList = issueService.findTop3();
        return top3TopIssueList;
    }

    @GetMapping("/issues")  // "/api/issue/issues"
    @ApiOperation(value = "Get Issues", response = PageableDTO.class)
    public PageableDTO<?> getIssues(Pageable pageable) {
        return issueService.findAll(pageable);
    }

    @PostMapping("/issues")  // "/api/issue/issues"
    @ApiOperation(value = "Get Issues", response = PageableDTO.class)
    public PageableDTO<?> getIssuesWithFilter(@RequestBody(required = false) IssuesFilterDTO issueFilter, Pageable pageable) throws ParseException {
        return issueService.findAllByFilter(pageable, issueFilter);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Issue By Id", response = IssueDTO.class)
    public IssueDTO getIssue(@PathVariable Long id) {
        return issueService.getById(id);
    }

    // /api/issue/impactTypes
    @GetMapping("/impactTypes")
    @ApiOperation(value = "Get impactTypes", response = List.class)
    public List<ImpactType> getImpactTypes() {
        List<ImpactType> impactTypes = Arrays.asList(ImpactType.values());
        return impactTypes;
    }

    // /api/issue/
    @PutMapping()
    @ApiOperation(value = "Update issue", response = IssueDTO.class)
    public IssueDTO updateIssue(@RequestBody(required = false) IssueDTO issueDTO, @CurrentUser User user) {
        return issueService.updateIssue(issueDTO, user);
    }

    // /api/issue/{id}
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete issue", response = ResponseStatus.class)
    public ResponseEntity<?> deleteIssue(@PathVariable Long id) {
        return issueService.deleteIssueById(id);
    }

    // /api/issue/changeStatus/{id}
    @PutMapping("/changeStatus/{id}")
    public ResponseEntity<?> changeIssueStatusById(@PathVariable Long id,@RequestBody(required = true) IssueDTO status,@CurrentUser User user){
        Boolean booleanStatus = status.getStatus();
        return issueService.changeIssueStatusById(booleanStatus,id,user);
    }
}
