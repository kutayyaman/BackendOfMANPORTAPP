package com.kutay.MANPORT.ws.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kutay.MANPORT.ws.domain.Issue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "Issue Data Transfer Object")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueDTO {
    @ApiModelProperty(required = true, value = "id")
    private long id;
    @ApiModelProperty(required = true, value = "name")
    private String name;
    @ApiModelProperty(required = true, value = "description")
    private String description;
    @ApiModelProperty(required = true, value = "impact")
    private String impact;
    @ApiModelProperty(required = true, value = "createdDate")
    private String createdDate;
    @ApiModelProperty(required = true, value = "appName")
    private String appShortName;
    @ApiModelProperty(required = true, value = "appId")
    private Long appId;
    @ApiModelProperty(required = true, value = "jobName")
    private String jobName;
    @ApiModelProperty(required = true, value = "jobInterfaceId")
    private Long jobInterfaceId;
    @ApiModelProperty(required = true, value = "countryName")
    private String countryName;
    @ApiModelProperty(required = true, value = "countryId")
    private Long countryId;
    @ApiModelProperty(required = true, value = "status")
    private Boolean status = true;
    @ApiModelProperty(required = true, value = "track")
    private Boolean track = true;
    @ApiModelProperty(required = true, value = "serverName")
    private String serverName;
    @ApiModelProperty(required = true, value = "serverId")
    private Long serverId;

    public IssueDTO(Issue issue) {
        this.id = issue.getId();
        this.name = issue.getName();
        this.description = issue.getDescription();
        this.impact = issue.getImpactType().toString();
        this.createdDate = issue.getCreatedDate();
        this.appShortName = issue.getApplication().getShortName();
        this.jobName = issue.getJobImplement().getJobInterface().getName();
        this.countryName = issue.getJobImplement().getServer().getCountry().getName();
        this.status = issue.getStatus();
    }
}
