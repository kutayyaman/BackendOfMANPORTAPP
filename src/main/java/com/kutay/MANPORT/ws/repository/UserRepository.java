package com.kutay.MANPORT.ws.repository;

import com.kutay.MANPORT.ws.domain.RowStatus;
import com.kutay.MANPORT.ws.domain.Team;
import com.kutay.MANPORT.ws.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAndRowStatus(String email, RowStatus rowStatus);

    List<User> findAllByRowStatusAndTeam(RowStatus rowStatus, Team team);

    User findFirstByIdAndRowStatus(Long id, RowStatus rowStatus);

    User findFirstByIdAndTeamAndRowStatus(Long id, Team team, RowStatus rowStatus);
}
