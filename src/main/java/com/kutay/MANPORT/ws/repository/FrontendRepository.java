package com.kutay.MANPORT.ws.repository;

import com.kutay.MANPORT.ws.domain.Frontend;
import com.kutay.MANPORT.ws.domain.RowStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FrontendRepository extends JpaRepository<Frontend, Long> {
    List<Frontend> findAllByRowStatus(RowStatus rowStatus);
    Frontend findFirstByIdAndRowStatus(Long id, RowStatus rowStatus);
}
