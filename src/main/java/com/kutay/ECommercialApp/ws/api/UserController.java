package com.kutay.ECommercialApp.ws.api;

import com.kutay.ECommercialApp.ws.models.User;
import com.kutay.ECommercialApp.ws.service.IUserService;
import com.kutay.ECommercialApp.ws.shared.GenericResponse;
import com.kutay.ECommercialApp.ws.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(ApiPaths.UserCtrl.CTRL)
@Api(value = ApiPaths.UserCtrl.CTRL, description = "User APIs")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }


    @PostMapping()
    @ApiOperation(value = "Create User Operation", response = User.class)
    public GenericResponse createUser(@RequestBody User user) {
        userService.save(user);
        GenericResponse response = new GenericResponse("user created");
        return response;
    }
}
