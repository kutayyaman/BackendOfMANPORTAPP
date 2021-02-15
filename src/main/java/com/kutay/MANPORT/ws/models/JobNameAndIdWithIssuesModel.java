package com.kutay.MANPORT.ws.models;

import com.kutay.MANPORT.ws.domain.Issue;
import com.kutay.MANPORT.ws.dto.IssueDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class JobNameAndIdWithIssuesModel {
    private Long jobId;
    private String jobName;
    private List<IssueDTO> issueDTOList = new ArrayList<>();
}
