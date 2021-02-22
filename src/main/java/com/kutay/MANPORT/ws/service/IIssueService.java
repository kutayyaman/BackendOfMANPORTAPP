package com.kutay.MANPORT.ws.service;

import com.kutay.MANPORT.ws.MyAnnotations.CurrentUser;
import com.kutay.MANPORT.ws.domain.Issue;
import com.kutay.MANPORT.ws.domain.RowStatus;
import com.kutay.MANPORT.ws.domain.User;
import com.kutay.MANPORT.ws.dto.IssueDTO;
import com.kutay.MANPORT.ws.dto.IssuesFilterDTO;
import com.kutay.MANPORT.ws.dto.PageableDTO;
import com.kutay.MANPORT.ws.dto.TopIssueDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.ParseException;
import java.util.List;

public interface IIssueService {
    List<TopIssueDTO> findTop3();

    PageableDTO<?> findAll(Pageable pageable);

    PageableDTO<?> findAllByFilter(Pageable pageable, IssuesFilterDTO issuesFilterDTO) throws ParseException;

    IssueDTO getById(Long id);

    IssueDTO updateIssue(IssueDTO issueDTO, User user);

    ResponseEntity<?> deleteIssueById(Long id);

    ResponseEntity<?> changeIssueStatusById(Boolean status, Long id, User user);
}
