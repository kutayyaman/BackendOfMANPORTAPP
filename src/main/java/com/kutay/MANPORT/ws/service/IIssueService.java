package com.kutay.MANPORT.ws.service;

import com.kutay.MANPORT.ws.domain.Issue;
import com.kutay.MANPORT.ws.domain.RowStatus;
import com.kutay.MANPORT.ws.dto.IssueDTO;
import com.kutay.MANPORT.ws.dto.PageableDTO;
import com.kutay.MANPORT.ws.dto.TopIssueDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IIssueService {
    List<TopIssueDTO> findTop3();
    PageableDTO<?> findAll(Pageable pageable);
}
