package com.kutay.MANPORT.ws.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kutay.MANPORT.ws.domain.Country;
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
public class CountryDTO {
    @ApiModelProperty(required = true,value = "id")
    private long id;
    @ApiModelProperty(required = true,value = "name")
    private String name;

    public CountryDTO(Country country){
        this.id=country.getId();
        this.name=country.getName();
    }
}
