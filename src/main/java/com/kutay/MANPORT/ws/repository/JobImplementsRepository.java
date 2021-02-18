package com.kutay.MANPORT.ws.repository;

import com.kutay.MANPORT.ws.domain.JobImplement;
import com.kutay.MANPORT.ws.domain.RowStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobImplementsRepository extends JpaRepository<JobImplement, Long> {
    int countAllByRowStatus(RowStatus rowStatus);
    List<JobImplement> findAllByRowStatus(RowStatus rowStatus);
}
