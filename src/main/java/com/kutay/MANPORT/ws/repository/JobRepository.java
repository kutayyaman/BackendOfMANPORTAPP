package com.kutay.MANPORT.ws.repository;

import com.kutay.MANPORT.ws.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {
}
