package com.kutay.MANPORT.ws.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Application extends BaseEntity {
    private String fullName;
    @Enumerated(EnumType.STRING)
    private BusinessAreaType businessArea;
    private int livePlantsCount;
    private String shortName;
    private String releaseDate;
    private int lineCountOfBackend;
    private int lineCountOfFrontend;
    @OneToMany(mappedBy = "application", fetch = FetchType.LAZY)
    private List<Job> jobs = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User applicationManager;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "APPLICATION_COUNTRY",
            joinColumns = @JoinColumn(name = "APPLICATION_ID"),
            inverseJoinColumns = @JoinColumn(name = "COUNTRY_ID"))
    private Set<Country> countries = new HashSet<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "APPLICATION_SERVERROOM",
            joinColumns = @JoinColumn(name = "APPLICATION_ID"),
            inverseJoinColumns = @JoinColumn(name = "SERVERROOM_ID"))
    private Set<ServerRoom> serverRooms = new HashSet<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "backendId")
    private Backend backend;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "frontendId")
    private Frontend frontend;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "databaseId")
    private Frontend database;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamId")
    private Team team;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "APPLICATION_PLANT",
            joinColumns = @JoinColumn(name = "APPLICATION_ID"),
            inverseJoinColumns = @JoinColumn(name = "PLANT_ID"))
    private Set<Plant> plants = new HashSet<>();
}
