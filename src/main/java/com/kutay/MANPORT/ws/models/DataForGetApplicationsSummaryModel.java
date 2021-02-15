package com.kutay.MANPORT.ws.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DataForGetApplicationsSummaryModel {
    private Long appId;
    private String appName;
    List<ACountryWithServers> aCountryWithServersList = new ArrayList<>();
}
