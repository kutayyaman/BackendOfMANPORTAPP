package com.kutay.MANPORT.ws.repository;

import com.kutay.MANPORT.ws.domain.Application;
import com.kutay.MANPORT.ws.domain.Issue;
import com.kutay.MANPORT.ws.domain.JobImplement;
import com.kutay.MANPORT.ws.domain.RowStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Date;
import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findTop3ByRowStatusOrderByIdDesc(RowStatus rowStatus);

    List<Issue> findAllByJobImplementAndRowStatus(JobImplement jobImplement, RowStatus rowStatus);

    int countAllByRowStatus(RowStatus rowStatus);

    Page<Issue> findAllByRowStatus(RowStatus rowStatus, Pageable pageable);

    Page<Issue> findAllByRowStatusAndApplication(RowStatus rowStatus, Application application, Pageable pageable);

    @Query(
            "select I " +
                    "from Issue I " +
                    "where I.rowStatus = :rowStatus " +
                    "and I.createdDate>= :dateFrom " +
                    "and I.createdDate<= :dateTo " +
                    "and I.application= :application "
    )
    Page<Issue> findAllByRowStatusAndApplicationAndCreatedDateBetween(RowStatus rowStatus, Application application, String dateFrom, String dateTo, Pageable pageable);

    @Query(
            "select I " +
                    "from Issue I " +
                    "where I.rowStatus = :rowStatus " +
                    "and I.createdDate>= :dateFrom " +
                    "and I.createdDate<= :dateTo " +
                    "and I.application= :application " +
                    "and I.status= :status"
    )
    Page<Issue> findAllByRowStatusAndApplicationAndStatusAndCreatedDateBetween(RowStatus rowStatus, Application application, boolean status, String dateFrom, String dateTo, Pageable pageable);

    @Query(
            "select I " +
                    "from Issue I " +
                    "where I.rowStatus = :rowStatus " +
                    "and I.createdDate>= :dateFrom " +
                    "and I.createdDate<= :dateTo " +
                    "and I.status= :status"
    )
    Page<Issue> findAllByRowStatusAndStatusAndCreatedDateBetween(RowStatus rowStatus, boolean status, String dateFrom, String dateTo, Pageable pageable);

    @Query(
            "select I " +
                    "from Issue I " +
                    "where I.rowStatus = :rowStatus " +
                    "and I.createdDate>= :dateFrom " +
                    "and I.createdDate<= :dateTo "
    )
    Page<Issue> findAllByRowStatusAndCreatedDateBetween(RowStatus rowStatus, String dateFrom, String dateTo, Pageable pageable);

    Issue findFirstByIdAndRowStatus(Long id, RowStatus rowStatus);

    /*@Query(
            "update Issue I " +
                    "set I.rowStatus = com.kutay.MANPORT.ws.domain.RowStatus.DELETED " +
                    "where I.id = :id "
    )
    void deleteIssueById(@Param("id")Long id,@Param("rowStatus")RowStatus rowStatus);*/ //bu queriy calismiyor sebebini arastir
}
