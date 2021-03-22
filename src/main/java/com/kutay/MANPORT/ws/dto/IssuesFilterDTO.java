package com.kutay.MANPORT.ws.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@ApiModel(value = "Issues Filter Data Transfer Object")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class IssuesFilterDTO {
    @ApiModelProperty(required = true,value = "selectedFromDate")
    private Date selectedFromDate;
    @ApiModelProperty(required = true,value = "selectedToDate")
    private Date selectedToDate;
    @ApiModelProperty(required = true,value = "appId")
    private Long appId;
    @ApiModelProperty(required = true,value = "status")
    private Boolean status;
}
