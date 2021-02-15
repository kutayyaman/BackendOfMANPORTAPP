package com.kutay.MANPORT.ws.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Application extends BaseEntity{
    private String shortName;
    private String fullName;
    @OneToMany(mappedBy = "application", fetch = FetchType.EAGER)
    private List<JobInterface> jobInterfaces = new ArrayList<>();
}
