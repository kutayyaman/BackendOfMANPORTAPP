package com.kutay.MANPORT.ws.models;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ApplicationWithACountryAndServers {
    private Long applicationId;
    private String applicationName;
    private Long countryId;
    private String countryName;
    private List<ServerNameAndId> serverNameAndIdList = new ArrayList<>();
}
