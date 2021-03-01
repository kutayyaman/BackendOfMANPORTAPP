package com.kutay.MANPORT.ws.service;

import com.kutay.MANPORT.ws.domain.RowStatus;
import com.kutay.MANPORT.ws.domain.Team;
import com.kutay.MANPORT.ws.domain.User;
import com.kutay.MANPORT.ws.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {
    ResponseEntity<?> save(UserDTO userDTO);

    User findByEmail(String email);

    List<UserDTO> getUsersByTeamId(Long teamId);

    User findById(Long id);

    User findByIdAndTeamId(Long id, Long teamId);
}
