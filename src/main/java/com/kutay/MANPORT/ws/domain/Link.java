package com.kutay.MANPORT.ws.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Link extends BaseEntity {
    private String name;
    private String url;
    @Enumerated(EnumType.STRING)
    private LinkEnvironmentType linkEnvironmentType;
    @Enumerated(EnumType.STRING)
    private LinkType linkType;
    @Enumerated(EnumType.STRING)
    private LinkSpecificType linkSpecificType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicationServerId")
    private ApplicationServer applicationServer;
}
