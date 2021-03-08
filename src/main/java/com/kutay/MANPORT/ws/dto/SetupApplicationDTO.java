package com.kutay.MANPORT.ws.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@ApiModel(value = "Setup Application Data Transfer Object")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetupApplicationDTO {
    @ApiModelProperty(required = true,value = "id")
    @NotNull
    private Long appId;
    @ApiModelProperty(required = true,value = "serverId")
    @NotNull
    private Long serverId;
    @ApiModelProperty(required = true,value = "countryId")
    @NotNull
    private Long countryId;
}
