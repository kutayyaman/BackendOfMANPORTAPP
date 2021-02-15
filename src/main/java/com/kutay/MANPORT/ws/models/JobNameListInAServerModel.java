package com.kutay.MANPORT.ws.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class JobNameListInAServerModel {
    private Long serverId;
    private String serverName;
    private List<String> jobNames = new ArrayList<>();
}
