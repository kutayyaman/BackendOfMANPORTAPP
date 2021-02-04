package com.kutay.MANPORT.ws.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Job extends BaseEntity {
    private String name;
    @OneToMany(mappedBy = "job", fetch = FetchType.LAZY)
    private List<Issue> issues = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicationId")
    private Application application;
}
