package com.kutay.MANPORT.ws.repository;

import com.kutay.MANPORT.ws.domain.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findTop3ByOrderByIdDesc();
}
