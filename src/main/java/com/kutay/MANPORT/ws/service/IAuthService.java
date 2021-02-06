package com.kutay.MANPORT.ws.service;

import com.kutay.MANPORT.ws.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface IAuthService {
    ResponseEntity<?> auth(User user);
}
