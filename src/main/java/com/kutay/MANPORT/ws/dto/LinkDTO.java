package com.kutay.MANPORT.ws.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kutay.MANPORT.ws.domain.Country;
import com.kutay.MANPORT.ws.domain.Link;
import com.kutay.MANPORT.ws.domain.LinkEnvironmentType;
import com.kutay.MANPORT.ws.domain.LinkType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@ApiModel(value = "Server Data Transfer Object")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkDTO {
    @ApiModelProperty(required = true,value = "id")
    private long id;
    @ApiModelProperty(required = true,value = "name")
    private String name;
    @ApiModelProperty(required = true,value = "url")
    private String url;
    @ApiModelProperty(required = true,value = "linkEnvironmentType")
    private String linkEnvironmentType;
    @ApiModelProperty(required = true,value = "linkType")
    private String linkType;
    @ApiModelProperty(required = true,value = "linkType")
    private String linkSpecificType;
    @ApiModelProperty(required = true,value = "countryId")
    private Long countryId;
    @ApiModelProperty(required = true,value = "countryName")
    private String countryName;

    public LinkDTO(Link link){
        setId(link.getId());
        setName(link.getName());
        setUrl(link.getUrl());
        setLinkEnvironmentType(link.getLinkEnvironmentType().toString());
        setLinkType(link.getLinkType().toString());
        Country country = link.getApplicationServer().getServer().getCountry();
        setCountryId(country.getId());
        setCountryName(country.getName());
        setLinkSpecificType(link.getLinkSpecificType().toString());
    }

}
