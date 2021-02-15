package com.kutay.MANPORT.ws.service;

import com.kutay.MANPORT.ws.domain.Country;
import com.kutay.MANPORT.ws.domain.Server;
import com.kutay.MANPORT.ws.models.GetApplicationsSummaryModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IApplicationSummaryService {
    GetApplicationsSummaryModel getApplicationsSummary();
}
