package com.kutay.MANPORT.ws.service;

import com.kutay.MANPORT.ws.domain.User;
import org.springframework.http.ResponseEntity;

public interface IAuthService {
    ResponseEntity<?> auth(User user);
}
