package com.kutay.MANPORT.ws.service.Impl;

import com.kutay.MANPORT.ws.domain.User;
import com.kutay.MANPORT.ws.dto.UserDTO;
import com.kutay.MANPORT.ws.service.IAuthService;
import com.kutay.MANPORT.ws.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {
    private final IUserService userService;

    public AuthServiceImpl(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> auth(User user) {
        //User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //yukardaki gibi almak yerine Authentication'i controllerda injecte edip buraya yolladik onun icinden alcaz
        //User user = (User) authentication.getPrincipal();
        //Artik bu yukardaki gibide almamiza gerek kalmadi biz direkt buraya user'i yolladik yollarkende AuthController'da handleAuthentication methodunun parametre kisminda @CurrentUser isimli kendi yazdigimiz anatasyonu kullandik.

        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setEmail(user.getEmail());
        userDTO.setRowStatus(null);
        return ResponseEntity.ok(userDTO);
    }
}
