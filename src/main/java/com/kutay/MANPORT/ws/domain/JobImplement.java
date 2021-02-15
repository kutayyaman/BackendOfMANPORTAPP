package com.kutay.MANPORT.ws.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class JobImplement extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jobInterfaceId")
    private JobInterface jobInterface;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serverId")
    private Server server;
    @OneToMany(mappedBy = "jobImplement", fetch = FetchType.LAZY)
    private List<Issue> issues = new ArrayList<>();
}
