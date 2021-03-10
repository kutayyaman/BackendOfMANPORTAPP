package com.kutay.MANPORT.ws.repository;

import com.kutay.MANPORT.ws.domain.Application;
import com.kutay.MANPORT.ws.domain.Issue;
import com.kutay.MANPORT.ws.domain.JobInterface;
import com.kutay.MANPORT.ws.domain.RowStatus;
import com.kutay.MANPORT.ws.dto.ApplicationDropListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findAllByRowStatus(RowStatus rowStatus);

    List<Application> findAllByRowStatusAndTrack(RowStatus rowStatus, Boolean bool);

    @Query("select a " +
            "from Application a " +
            "left join fetch a.applicationServers appServers " +
            "left join fetch appServers.server " +
            "where a.id= :id " +
            "and a.rowStatus = :rowStatus ")
    Application findAllByIdAndRowStatusWithApplicationServers(Long id, RowStatus rowStatus);

    @Query("select distinct a " +
            "from Application a " +
            "left join fetch a.jobInterfaces I " +
            "where a.rowStatus = :rowStatus")
    List<Application> findAllByRowStatusWithInterfaces(@Param("rowStatus") RowStatus rowStatus);

    int countAllByRowStatus(RowStatus rowStatus);

    Page<Application> findAllByRowStatus(RowStatus rowStatus, Pageable pageable);

    @Query("select new com.kutay.MANPORT.ws.dto.ApplicationDropListDTO( " +
            "a.id, " +
            "a.shortName) " +
            "from Application a " +
            "where a.rowStatus = :rowStatus")
    Page<ApplicationDropListDTO> findAllAppDropListDTOByRowStatusAndPage(@Param("rowStatus") RowStatus rowStatus, Pageable pageable);

    @Query("select new com.kutay.MANPORT.ws.dto.ApplicationDropListDTO( " +
            "a.id, " +
            "a.shortName) " +
            "from Application a " +
            "where a.rowStatus = :rowStatus")
    List<ApplicationDropListDTO> findAllAppDropListDTOByRowStatus(@Param("rowStatus") RowStatus rowStatus);

    Application findFirstByJobInterfaces(JobInterface jobInterface);

    Application findFirstById(Long id);

    Application findFirstByIdAndRowStatus(Long id, RowStatus rowStatus);
}
