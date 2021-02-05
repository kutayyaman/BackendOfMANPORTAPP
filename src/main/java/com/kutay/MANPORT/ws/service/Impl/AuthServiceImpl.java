package com.kutay.MANPORT.ws.service.Impl;

import com.kutay.MANPORT.ws.domain.User;
import com.kutay.MANPORT.ws.dto.UserDTO;
import com.kutay.MANPORT.ws.error.ApiError;
import com.kutay.MANPORT.ws.service.IAuthService;
import com.kutay.MANPORT.ws.service.IUserService;
import com.kutay.MANPORT.ws.util.ApiPaths;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements IAuthService {
    private final IUserService userService;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthServiceImpl(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> auth(String authorization) {
        if (authorization == null) {
            ApiError error = new ApiError(401, "Unauthorized request", ApiPaths.AuthCtrl.CTRL);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
        String base64encoded = authorization.split("Basic ")[1];
        String decoded = new String(Base64.getDecoder().decode(base64encoded));
        String[] parts = decoded.split(":");
        String email = parts[0];
        String password = parts[1];
        User user = userService.findByEmail(email);
        if (user == null) {
            ApiError error = new ApiError(401, "Unauthorized request", ApiPaths.AuthCtrl.CTRL);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
        String hashedPassword = user.getPassword();
        if (!passwordEncoder.matches(password, hashedPassword)) {
            ApiError error = new ApiError(401, "Unauthorized request", ApiPaths.AuthCtrl.CTRL);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setEmail(user.getEmail());
        userDTO.setRowStatus(null);
        return ResponseEntity.ok(userDTO);
    }
}
