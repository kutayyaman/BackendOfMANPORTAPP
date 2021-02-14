package com.kutay.MANPORT.ws.service;

import com.kutay.MANPORT.ws.domain.Issue;
import com.kutay.MANPORT.ws.dto.TopIssueDTO;

import java.util.List;

public interface IIssueService {
    List<TopIssueDTO> findTop3();
}
