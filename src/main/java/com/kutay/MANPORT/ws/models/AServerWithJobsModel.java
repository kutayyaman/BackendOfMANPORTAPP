package com.kutay.MANPORT.ws.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AServerWithJobsModel {
    private Long serverId;
    private String serverName;
    private String highestImpactOfServer;
    private List<AJobWithIssuesModel> jobAndIssues = new ArrayList<>();
}
