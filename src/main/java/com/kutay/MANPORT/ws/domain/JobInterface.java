package com.kutay.MANPORT.ws.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class JobInterface extends BaseEntity{
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicationId")
    private Application application;
    @OneToMany(mappedBy = "jobInterface", fetch = FetchType.LAZY)
    private List<JobImplement> jobImplements = new ArrayList<>();
}
