package com.kutay.MANPORT.ws.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Country extends BaseEntity {
    private String name;
    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<Server> servers = new ArrayList<>();
}
