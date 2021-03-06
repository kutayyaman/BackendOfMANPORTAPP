package com.kutay.MANPORT.ws.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "Country Data Transfer Object")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagementFactoryDTO {
    @ApiModelProperty(required = true, value = "countryDTO")
    private CountryDTO countryDTO;
    @ApiModelProperty(required = true, value = "track")
    private boolean track = true;
    @ApiModelProperty(required = true, value = "alive")
    private boolean alive = true;
}
