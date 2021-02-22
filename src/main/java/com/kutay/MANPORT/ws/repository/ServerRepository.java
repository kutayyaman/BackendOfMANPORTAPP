package com.kutay.MANPORT.ws.repository;

import com.kutay.MANPORT.ws.domain.Country;
import com.kutay.MANPORT.ws.domain.RowStatus;
import com.kutay.MANPORT.ws.domain.Server;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServerRepository extends JpaRepository<Server, Long> {
    List<Server> findAllByRowStatus(RowStatus rowStatus);
    List<Server> findAllByCountryAndRowStatus(Country country,RowStatus rowStatus);
    int countAllByRowStatus(RowStatus rowStatus);
    Server findFirstByIdAndRowStatus(Long id, RowStatus rowStatus);
}
