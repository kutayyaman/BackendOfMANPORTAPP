package com.kutay.MANPORT.ws.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kutay.MANPORT.ws.domain.Application;
import com.kutay.MANPORT.ws.domain.BusinessAreaType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "Application Data Transfer Object For Management Page")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDTOForManagementPage {
    @ApiModelProperty(required = true,value = "id")
    private long id;
    @ApiModelProperty(required = true,value = "shortName")
    private String shortName;
    @ApiModelProperty(required = true,value = "businessAreaType")
    private String businessAreaType;  //it should convert to string before set
    @ApiModelProperty(required = true,value = "livePlants")
    private int livePlants;           //it should calculate
    @ApiModelProperty(required = true,value = "lineStopRisk")
    private boolean lineStopRisk;
    @ApiModelProperty(required = true,value = "track")
    private boolean track;

    public ApplicationDTOForManagementPage(Application application){
        this.id = application.getId();
        this.shortName = application.getShortName();
        this.businessAreaType = application.getBusinessAreaType().toString();
        this.lineStopRisk = application.isLineStopRisk();
        this.track = application.isTrack();
        this.livePlants = livePlantsOfApplication(application);
    }

    private int livePlantsOfApplication(Application application) {
        return application.getApplicationServers().size();
    }
}
