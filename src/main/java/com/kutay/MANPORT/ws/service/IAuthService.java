package com.kutay.MANPORT.ws.service;

import org.springframework.http.ResponseEntity;

public interface IAuthService {
    ResponseEntity<?> auth(String authorization);
}
