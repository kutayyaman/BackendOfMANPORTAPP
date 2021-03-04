package com.kutay.MANPORT.ws.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ApplicationServer extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicationId")
    private Application application;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serverId")
    private Server server;
    @OneToMany(mappedBy = "applicationServer", fetch = FetchType.LAZY)
    private List<Link> links = new ArrayList<>();
}
