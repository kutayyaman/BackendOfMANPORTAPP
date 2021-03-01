package com.kutay.MANPORT.ws.domain;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
public class Server extends BaseEntity {
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "countryId")
    private Country country;
    @OneToMany(mappedBy = "server", fetch = FetchType.EAGER)
    private List<JobImplement> jobImplements = new ArrayList<>();
    @OneToMany(mappedBy = "server", fetch = FetchType.LAZY)
    private List<ApplicationServer> applicationServers = new ArrayList<>();
}
