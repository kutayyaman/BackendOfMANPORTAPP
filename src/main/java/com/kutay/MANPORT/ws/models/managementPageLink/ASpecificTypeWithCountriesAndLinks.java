package com.kutay.MANPORT.ws.models.managementPageLink;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ASpecificTypeWithCountriesAndLinks {
    private String linkSpecificTypeName;
    List<ACountryNameWithLinks> aCountryNameWithLinksList = new ArrayList();
}
