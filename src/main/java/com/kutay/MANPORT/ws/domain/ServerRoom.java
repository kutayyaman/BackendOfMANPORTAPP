package com.kutay.MANPORT.ws.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class ServerRoom extends BaseEntity {
    private String name;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "serverRooms")
    private Collection<Application> applications = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "countryId")
    private Country country;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plantId")
    private Plant plant;
}
