package com.kutay.MANPORT.ws.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ApplicationServer extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicationId")
    private Application application;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serverId")
    private Server server;
}
