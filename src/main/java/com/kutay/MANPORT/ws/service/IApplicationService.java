package com.kutay.MANPORT.ws.service;

import com.kutay.MANPORT.ws.dto.ApplicationNameWithOtherDataListDTO;

import java.util.List;

public interface IApplicationService {
    List<ApplicationNameWithOtherDataListDTO> getApplicationsWithIssues();
}
