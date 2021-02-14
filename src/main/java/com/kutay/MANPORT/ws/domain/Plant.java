package com.kutay.MANPORT.ws.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
public class Plant extends BaseEntity { //fabrika
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "countryId")
    private Country country;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "plants")
    private Collection<Application> applications = new ArrayList<>();
    @OneToMany(mappedBy = "plant", fetch = FetchType.LAZY)
    private List<ServerRoom> serverRooms = new ArrayList<>();
}
