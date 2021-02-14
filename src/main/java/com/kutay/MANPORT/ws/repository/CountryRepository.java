package com.kutay.MANPORT.ws.repository;

import com.kutay.MANPORT.ws.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Long> {
}
