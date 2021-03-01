package com.kutay.MANPORT.ws.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kutay.MANPORT.ws.domain.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "Application Data Transfer Object")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDTO {
    @ApiModelProperty(required = true,value = "id")
    private long id;
    @ApiModelProperty(required = true,value = "shortName")
    private String shortName;
    @ApiModelProperty(required = true,value = "fullName")
    private String fullName;
    @ApiModelProperty(required = false,value = "lineStopRisk")
    private Boolean lineStopRisk;
    @ApiModelProperty(required = false,value = "track")
    private Boolean track;
    @ApiModelProperty(required = true,value = "businessAreaType")
    private String businessAreaType;
    @ApiModelProperty(required = true,value = "releaseDate")
    private String releaseDate;
    @ApiModelProperty(required = true,value = "responsibleUserName")
    private String responsibleUserName;
    @ApiModelProperty(required = true,value = "responsibleUserId")
    private Long responsibleUserId;
    @ApiModelProperty(required = true,value = "responsibleTeamName")
    private String responsibleTeamName;
    @ApiModelProperty(required = true,value = "responsibleTeamId")
    private Long responsibleTeamId;
    @ApiModelProperty(required = true,value = "backendId")
    private Long backendId;
    @ApiModelProperty(required = true,value = "backendName")
    private String backendName;
    @ApiModelProperty(required = true,value = "frontendId")
    private Long frontendId;
    @ApiModelProperty(required = true,value = "frontendName")
    private String frontendName;
    @ApiModelProperty(required = true,value = "databaseId")
    private Long databaseId;
    @ApiModelProperty(required = true,value = "databaseName")
    private String databaseName;
    @ApiModelProperty(required = true,value = "lineOfBackendCode")
    private int lineOfBackendCode;
    @ApiModelProperty(required = true,value = "lineOfFrontendCode")
    private int lineOfFrontendCode;


    public ApplicationDTO(Application application){
        this.setId(application.getId());
        this.setShortName(application.getShortName());
        this.setFullName(application.getFullName());
        this.setLineStopRisk(application.isLineStopRisk());
        this.setTrack(application.isTrack());
        this.setBusinessAreaType(application.getBusinessAreaType().toString());
        this.setReleaseDate(application.getReleaseDate());
        User user = application.getUser();
        this.setResponsibleUserName(user.getName());
        this.setResponsibleUserId(user.getId());
        Team team = user.getTeam();
        this.setResponsibleTeamName(team.getName());
        this.setResponsibleTeamId(team.getId());
        Database database = application.getDatabase();
        this.setDatabaseId(database.getId());
        this.setDatabaseName(database.getName());
        Backend backend = application.getBackend();
        this.setBackendId(backend.getId());
        this.setBackendName(backend.getName());
        Frontend frontend = application.getFrontend();
        this.setFrontendId(frontend.getId());
        this.setFrontendName(frontend.getName());
        this.setLineOfBackendCode(application.getLineOfBackendCode());
        this.setLineOfFrontendCode(application.getLineOfFrontendCode());
        this.setLineStopRisk(application.isLineStopRisk());
    }

}
