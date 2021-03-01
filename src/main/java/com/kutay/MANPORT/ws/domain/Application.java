package com.kutay.MANPORT.ws.domain;

import com.kutay.MANPORT.ws.dto.ApplicationDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
public class Application extends BaseEntity implements Cloneable {
    private String shortName;
    private String fullName;
    private boolean lineStopRisk = false;
    private boolean track = true;
    private String releaseDate;
    private int lineOfBackendCode;
    private int lineOfFrontendCode;
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "frontendId")
    private Frontend frontend;
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "backendId")
    private Backend backend;
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "databaseId")
    private Database database;
    @Enumerated(EnumType.STRING)
    private BusinessAreaType businessAreaType;
    @OneToMany(mappedBy = "application", fetch = FetchType.LAZY)
    private List<JobInterface> jobInterfaces = new ArrayList<>();
    @OneToMany(mappedBy = "application", fetch = FetchType.LAZY)
    private List<Issue> issues = new ArrayList<>();
    @OneToMany(mappedBy = "application", fetch = FetchType.LAZY)
    private List<ApplicationServer> applicationServers = new ArrayList<>();

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
