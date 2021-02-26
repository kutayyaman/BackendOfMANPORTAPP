package com.kutay.MANPORT.ws.api;

import com.kutay.MANPORT.ws.MyAnnotations.CurrentUser;
import com.kutay.MANPORT.ws.domain.User;
import com.kutay.MANPORT.ws.service.IAuthService;
import com.kutay.MANPORT.ws.service.IUserService;
import com.kutay.MANPORT.ws.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()// /api/auth
    @ApiOperation(value = "Handle Authentication Operation", response = ResponseEntity.class)
    public ResponseEntity<?> handleAuthentication(@CurrentUser User user) { //@CurrentUser anatasyonunu ben olusturdum.
        return authService.auth(user);
    }

}
