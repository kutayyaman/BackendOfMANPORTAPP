package com.kutay.MANPORT.ws.repository;

import com.kutay.MANPORT.ws.domain.RowStatus;
import com.kutay.MANPORT.ws.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findAllByRowStatus(RowStatus rowStatus);

    Team findFirstByIdAndRowStatus(Long id, RowStatus rowStatus);
}
