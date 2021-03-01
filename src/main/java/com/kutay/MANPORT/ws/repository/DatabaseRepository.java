package com.kutay.MANPORT.ws.repository;

import com.kutay.MANPORT.ws.domain.Database;
import com.kutay.MANPORT.ws.domain.RowStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DatabaseRepository extends JpaRepository<Database, Long> {
    List<Database> findAllByRowStatus(RowStatus rowStatus);

    Database findFirstByIdAndRowStatus(Long id, RowStatus rowStatus);
}
