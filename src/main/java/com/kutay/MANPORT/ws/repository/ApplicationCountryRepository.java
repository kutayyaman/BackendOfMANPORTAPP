package com.kutay.MANPORT.ws.repository;

import com.kutay.MANPORT.ws.domain.Application;
import com.kutay.MANPORT.ws.domain.ApplicationCountry;
import com.kutay.MANPORT.ws.domain.Country;
import com.kutay.MANPORT.ws.domain.RowStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationCountryRepository extends JpaRepository<ApplicationCountry, Long> {
    ApplicationCountry findFirstByApplicationAndCountryAndRowStatus(Application application, Country country, RowStatus rowStatus);

    //List<ApplicationCountry> findAllByApplicationAndRowStatus(Application application,RowStatus rowStatus);
    @Query("select ac " +
            "from ApplicationCountry ac " +
            "left join fetch ac.country c " +
            "where ac.application = :application " +
            "and ac.rowStatus = :rowStatus")
    List<ApplicationCountry> findAllByApplicationAndRowStatus(Application application,RowStatus rowStatus);


}
