package com.kutay.MANPORT.ws.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Backend extends BaseEntity{
    private String name;
    @OneToMany(mappedBy = "backend", fetch = FetchType.LAZY)
    private List<Application> applications = new ArrayList<>();
}

