package com.kutay.MANPORT.ws.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Frontend extends BaseService {
    @OneToMany(mappedBy = "frontend", fetch = FetchType.LAZY)
    private List<Application> applications = new ArrayList<>();
}
