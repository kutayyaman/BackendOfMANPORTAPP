package com.kutay.MANPORT.ws.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ACountryWithServers {
    private Long countryId;
    private String countryName;
    private String highestImpactOfCountry;
    private List<AServerWithJobsModel> AServerWithJobsList = new ArrayList<>();
}
