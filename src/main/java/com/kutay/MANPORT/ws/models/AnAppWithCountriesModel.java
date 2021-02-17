package com.kutay.MANPORT.ws.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnAppWithCountriesModel {
    private Long appId;
    private String appName;
    private String highestImpactOfApp;
    List<ACountryWithServers> aCountryWithServersList = new ArrayList<>();
}
