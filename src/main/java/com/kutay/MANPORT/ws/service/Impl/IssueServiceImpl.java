package com.kutay.MANPORT.ws.service.Impl;

import com.kutay.MANPORT.ws.domain.Issue;
import com.kutay.MANPORT.ws.dto.TopIssueDTO;
import com.kutay.MANPORT.ws.repository.IssueRepository;
import com.kutay.MANPORT.ws.service.IIssueService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IssueServiceImpl implements IIssueService {
    private final IssueRepository issueRepository;

    public IssueServiceImpl(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @Override
    public List<TopIssueDTO> findTop3() {
        List<Issue> top3IssuesFromDB = issueRepository.findTop3ByOrderByIdDesc();

        List<TopIssueDTO> top3TopIssuesToReturn = convertIssueListToTopIssueDTOList(top3IssuesFromDB);

        return top3TopIssuesToReturn;
    }

    private List<TopIssueDTO> convertIssueListToTopIssueDTOList(List<Issue> top3IssuesFromDB) {
        List<TopIssueDTO> top3TopIssuesToReturn = new ArrayList<>();
        for (Issue issue : top3IssuesFromDB) {
            TopIssueDTO topIssueDTO = new TopIssueDTO();
            topIssueDTO.setId(issue.getId());
            topIssueDTO.setCreatedDate(issue.getCreatedDate());
           /* topIssueDTO.setApplicationFullName(issue.getApplication().getFullName());
            topIssueDTO.setApplicationShortName(issue.getApplication().getShortName());
            topIssueDTO.setJobName(issue.getJob().getName());*/
            top3TopIssuesToReturn.add(topIssueDTO);
        }
        return top3TopIssuesToReturn;
    }
}
