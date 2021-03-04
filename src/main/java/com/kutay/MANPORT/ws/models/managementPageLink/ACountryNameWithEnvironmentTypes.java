package com.kutay.MANPORT.ws.models.managementPageLink;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ACountryNameWithEnvironmentTypes {
    private String countryName;
    private List<AnEnvironmentTypeWithLinks> anEnvironmentTypeWithLinks = new ArrayList<>();
}
