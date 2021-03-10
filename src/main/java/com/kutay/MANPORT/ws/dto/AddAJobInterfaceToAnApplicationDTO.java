package com.kutay.MANPORT.ws.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "AddAJobInterfaceToAnApplication Data Transfer Object")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAJobInterfaceToAnApplicationDTO {
    @ApiModelProperty(required = true, value = "appId")
    private Long appId;
    @ApiModelProperty(required = true, value = "jobInterfaceName")
    private String jobInterfaceName;
}