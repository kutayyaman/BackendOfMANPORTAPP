package com.kutay.MANPORT.ws.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Plant extends BaseEntity {
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "countryId")
    private Country country;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "plants")
    private Set<Application> applications = new HashSet<>();
}
