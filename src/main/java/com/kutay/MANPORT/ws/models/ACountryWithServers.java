package com.kutay.MANPORT.ws.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ACountryWithServers {
    private Long countryId;
    private String countryName;
    private List<JobNameListInAServerModel> jobNameListInAServerModelList = new ArrayList<>();
}
