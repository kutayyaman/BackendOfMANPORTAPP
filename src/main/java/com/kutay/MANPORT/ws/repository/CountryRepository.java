package com.kutay.MANPORT.ws.repository;

import com.kutay.MANPORT.ws.domain.Country;
import com.kutay.MANPORT.ws.domain.RowStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {
    List<Country> findAllByRowStatus(RowStatus rowStatus);
    int countAllByRowStatus(RowStatus rowStatus);
}
