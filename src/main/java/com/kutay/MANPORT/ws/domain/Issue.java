package com.kutay.MANPORT.ws.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Issue extends BaseEntity {
    private String name;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jobImplementId")
    private JobImplement jobImplement;
    @Enumerated(EnumType.STRING)
    private ImpactType impactType;
    private Boolean status=true;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="applicationId")
    private Application application;
}
