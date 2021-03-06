package com.kutay.MANPORT.ws.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kutay.MANPORT.ws.domain.Application;
import com.kutay.MANPORT.ws.domain.ApplicationCountry;
import com.kutay.MANPORT.ws.domain.Country;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@ApiModel(value = "ApplicationCountry Data Transfer Object")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationCountryDTO {
    @ApiModelProperty(required = true, value = "id")
    private long id;
    @ApiModelProperty(required = true, value = "applicationName")
    private String applicationFullName;
    @ApiModelProperty(required = true, value = "applicationId")
    private Long applicationId;
    @ApiModelProperty(required = true, value = "countryName")
    private String countryName;
    @ApiModelProperty(required = true, value = "countryId")
    private Long countryId;
    @ApiModelProperty(required = true, value = "track")
    private boolean track = true;
    @ApiModelProperty(required = true, value = "alive")
    private boolean alive = true;
    @ApiModelProperty(required = true, value = "count")
    private int count = 0;

    public ApplicationCountryDTO(ApplicationCountry applicationCountry) {
        this.id = applicationCountry.getId();
        Application application = applicationCountry.getApplication();
        this.applicationId = application.getId();
        this.applicationFullName = application.getFullName();
        Country country = applicationCountry.getCountry();
        this.countryId = country.getId();
        this.countryName = country.getName();
        this.track = applicationCountry.isTrack();
        this.alive = applicationCountry.isAlive();
        this.count = applicationCountry.getCount();

    }
}
