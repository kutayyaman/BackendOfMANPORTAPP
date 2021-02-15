package com.kutay.MANPORT.ws.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kutay.MANPORT.ws.domain.ImpactType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Issue Data Transfer Object")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TopIssueDTO {
    @ApiModelProperty(required = true,value = "id")
    private Long id;
    @ApiModelProperty(required = true,value = "createdDate")
    private String createdDate;
    @ApiModelProperty(required = true,value = "applicationShortName")
    private String applicationShortName;
    @ApiModelProperty(required = true,value = "jobName")
    private String jobName;
    @ApiModelProperty(required = true,value = "impactType")
    private String impactType;
    @ApiModelProperty(required = true,value = "serverName")
    private String serverName;
    @ApiModelProperty(required = true,value = "countryName")
    private String countryName;
}
