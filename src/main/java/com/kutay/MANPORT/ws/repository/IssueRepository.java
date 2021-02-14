package com.kutay.MANPORT.ws.repository;

import com.kutay.MANPORT.ws.domain.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue,Long> {
}
