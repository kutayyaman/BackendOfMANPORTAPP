package com.kutay.MANPORT.ws.repository;

import com.kutay.MANPORT.ws.domain.Issue;
import com.kutay.MANPORT.ws.domain.JobImplement;
import com.kutay.MANPORT.ws.domain.RowStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findTop3ByRowStatusOrderByIdDesc(RowStatus rowStatus);
    List<Issue> findAllByJobImplementAndRowStatus(JobImplement jobImplement,RowStatus rowStatus);
    int countAllByRowStatus(RowStatus rowStatus);
}
