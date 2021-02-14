package com.kutay.MANPORT.ws.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Country extends BaseEntity {
    private String name;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "countries")
    private Collection<Application> applications = new ArrayList<>();
    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private Collection<ServerRoom> serverRooms = new ArrayList<>();
    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private Collection<Plant> plants = new ArrayList<>();
}
