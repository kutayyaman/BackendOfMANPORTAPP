package com.kutay.MANPORT.ws.api;

import com.kutay.MANPORT.ws.error.ApiError;
import com.kutay.MANPORT.ws.service.IAuthService;
import com.kutay.MANPORT.ws.service.IUserService;
import com.kutay.MANPORT.ws.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping(ApiPaths.AuthCtrl.CTRL)
@Api(value = ApiPaths.AuthCtrl.CTRL, description = "Auth APIs")
public class AuthController {

    private final IUserService userService;
    private final IAuthService authService;

    public AuthController(IUserService userService, IAuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping()
    @ApiOperation(value = "Handle Authentication Operation", response = ResponseEntity.class)
    public ResponseEntity<?> handleAuthentication(@RequestHeader(name = "Authorization", required = false) String authorization) {
        return authService.auth(authorization);
    }

}
