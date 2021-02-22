package com.kutay.MANPORT.ws.repository;

import com.kutay.MANPORT.ws.domain.Application;
import com.kutay.MANPORT.ws.domain.JobInterface;
import com.kutay.MANPORT.ws.domain.RowStatus;
import com.kutay.MANPORT.ws.dto.JobInterfaceDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobInterfaceRepository extends JpaRepository<JobInterface, Long> {
    List<JobInterface> findAllByRowStatus(RowStatus rowStatus);

    int countAllByRowStatus(RowStatus status);

    @Query("select new com.kutay.MANPORT.ws.dto.JobInterfaceDTO( " +
            "j.id, " +
            "j.name) " +
            "from JobInterface j " +
            "where j.rowStatus = :rowStatus " +
            "and j.application.id= :applicationId")
    List<JobInterfaceDTO> findAllByRowStatusAndApplicationId(@Param("rowStatus") RowStatus rowStatus, @Param("applicationId") Long applicationId);
}
