package com.kutay.MANPORT.ws.repository;

import com.kutay.MANPORT.ws.domain.Application;
import com.kutay.MANPORT.ws.domain.Country;
import com.kutay.MANPORT.ws.domain.RowStatus;
import com.kutay.MANPORT.ws.domain.Server;
import com.kutay.MANPORT.ws.dto.PageableDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServerRepository extends JpaRepository<Server, Long> {
    List<Server> findAllByRowStatus(RowStatus rowStatus);

    List<Server> findAllByCountryAndRowStatus(Country country, RowStatus rowStatus);

    int countAllByRowStatus(RowStatus rowStatus);

    Server findFirstByIdAndRowStatus(Long id, RowStatus rowStatus);

    @Query("select s " +
            "from Server s " +
            "where s.rowStatus = :rowStatus ")
    Page<Server> findAllByRowStatus(RowStatus rowStatus, Pageable pageable);



    /*@Query("select s " +
            "from Server s " +
            "left join fetch s.applications a " + //coka cok iliskide direkt join fetch diyince calismadi left join fetch yapinca calisti arastir.
            "where s.rowStatus = :rowStatus")
    List<Server> findAllByRowStatusWithApplications(@Param("rowStatus") RowStatus rowStatus);*/
}
