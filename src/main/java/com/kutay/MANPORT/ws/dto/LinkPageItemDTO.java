package com.kutay.MANPORT.ws.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kutay.MANPORT.ws.models.managementPageLink.ALinkSpecificTypeWithLinks;
import com.kutay.MANPORT.ws.models.managementPageLink.ASpecificTypeWithCountriesAndEnvironment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "Link Page Item Data Transfer Object")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkPageItemDTO {
    @ApiModelProperty(required = true, value = "appName")
    private String appShortName;
    @ApiModelProperty(required = true, value = "aLinkSpecificTypeWithLinkList")
    private List<ASpecificTypeWithCountriesAndEnvironment> aSpecificTypeWithCountriesAndEnvironments = new ArrayList<>();
}
