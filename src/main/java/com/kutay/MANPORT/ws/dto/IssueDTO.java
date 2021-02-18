package com.kutay.MANPORT.ws.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "Issue Data Transfer Object")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class IssueDTO {
    @ApiModelProperty(required = true,value = "id")
    private long id;
    @ApiModelProperty(required = true,value = "name")
    private String name;
    @ApiModelProperty(required = true,value = "description")
    private String description;
    @ApiModelProperty(required = true,value = "impact")
    private String impact;
    @ApiModelProperty(required = true,value = "createdDate")
    private String createdDate;
    @ApiModelProperty(required = true,value = "appName")
    private String appShortName;
    @ApiModelProperty(required = true,value = "jobName")
    private String jobName;
    @ApiModelProperty(required = true,value = "countryName")
    private String countryName;
}
