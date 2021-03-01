package com.kutay.MANPORT.ws.repository;

import com.kutay.MANPORT.ws.domain.Backend;
import com.kutay.MANPORT.ws.domain.RowStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BackendRepository extends JpaRepository<Backend, Long> {
    List<Backend> findAllByRowStatus(RowStatus rowStatus);

    Backend findFirstByIdAndRowStatus(Long id, RowStatus rowStatus);
}
