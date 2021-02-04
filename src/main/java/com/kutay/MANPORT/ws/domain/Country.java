package com.kutay.MANPORT.ws.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Country extends BaseEntity {
    private String name;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "countries")
    private Set<Application> applications = new HashSet<>();
    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private Set<ServerRoom> serverRooms;
    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private Set<Plant> plants = new HashSet<>();
}
