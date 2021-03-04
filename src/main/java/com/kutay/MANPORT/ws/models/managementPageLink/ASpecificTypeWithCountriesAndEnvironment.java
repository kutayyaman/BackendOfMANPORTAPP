package com.kutay.MANPORT.ws.models.managementPageLink;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ASpecificTypeWithCountriesAndEnvironment {
    private String specificTypeName;
    private List<ACountryNameWithEnvironmentTypes> aCountryNameWithEnvironmentTypes = new ArrayList<>();
}
