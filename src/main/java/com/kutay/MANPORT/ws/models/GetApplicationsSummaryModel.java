package com.kutay.MANPORT.ws.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetApplicationsSummaryModel {
    List<AnAppWithCountriesModel> applicationsSummary = new ArrayList<>();
}
