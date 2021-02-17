package com.kutay.MANPORT.ws.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kutay.MANPORT.ws.dto.IssueDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AJobWithIssuesModel {
    private Long jobId;
    private String jobName;
    private String highestImpactOfJob;
    private List<IssueDTO> issueDTOList = new ArrayList<>();
}
