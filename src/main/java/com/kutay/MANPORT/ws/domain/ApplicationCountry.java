package com.kutay.MANPORT.ws.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"applicationId", "countryId"})
)
public class ApplicationCountry extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicationId")
    private Application application;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "countryId")
    private Country country;
    private boolean track = true;
    private boolean alive = true;
    private int count = 0; // bu application bu ulkeye her kuruldugunda bunun sayisi 1 artacak her silindiginde bir azalacak ki bu ulkede kac tane var bilelim
}
