package com.kutay.MANPORT.ws.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "Application Drop List Data Transfer Object")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDropListDTO {
    @ApiModelProperty(required = true,value = "id")
    private long id;
    @ApiModelProperty(required = true,value = "shortName")
    private String shortName;
}
