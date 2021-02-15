package com.kutay.MANPORT.ws.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetApplicationsSummaryModel {
    List<DataForGetApplicationsSummaryModel> applicationsSummary = new ArrayList<>();
}
