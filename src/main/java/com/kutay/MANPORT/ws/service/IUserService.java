package com.kutay.MANPORT.ws.service;

import com.kutay.MANPORT.ws.domain.User;
import com.kutay.MANPORT.ws.dto.UserDTO;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    ResponseEntity<?> save(UserDTO userDTO);
    User findByEmail(String email);
}
