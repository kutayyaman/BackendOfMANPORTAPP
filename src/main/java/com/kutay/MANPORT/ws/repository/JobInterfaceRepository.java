package com.kutay.MANPORT.ws.repository;

import com.kutay.MANPORT.ws.domain.JobInterface;
import com.kutay.MANPORT.ws.domain.RowStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobInterfaceRepository extends JpaRepository<JobInterface, Long> {
    List<JobInterface> findAllByRowStatus(RowStatus rowStatus);
    int countAllByRowStatus(RowStatus status);
}
