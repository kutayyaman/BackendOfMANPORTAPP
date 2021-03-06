package com.kutay.MANPORT.ws.repository;

import com.kutay.MANPORT.ws.domain.Application;
import com.kutay.MANPORT.ws.domain.Country;
import com.kutay.MANPORT.ws.domain.RowStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {
    List<Country> findAllByRowStatus(RowStatus rowStatus);

    int countAllByRowStatus(RowStatus rowStatus);

    @Query("select c " +
            "from Country c " +
            "left join fetch c.applicationCountries ac " +
            "where ac.application = :application " +
            "and ac.rowStatus = :rowStatus")
    List<Country> findAllByAppAndRowStatus(Application application, RowStatus rowStatus);

    Country findFirstByIdAndRowStatus(Long id, RowStatus rowStatus);
}
