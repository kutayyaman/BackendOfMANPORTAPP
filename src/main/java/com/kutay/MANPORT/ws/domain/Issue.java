package com.kutay.MANPORT.ws.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Issue extends BaseEntity {
    private String name;
    private String impact;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jobId")
    private Job job;
}
