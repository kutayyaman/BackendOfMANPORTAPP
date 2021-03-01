package com.kutay.MANPORT.ws.api;

import com.kutay.MANPORT.ws.dto.TeamDTO;
import com.kutay.MANPORT.ws.dto.UserDTO;
import com.kutay.MANPORT.ws.service.IUserService;
import com.kutay.MANPORT.ws.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


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
    @ApiOperation(value = "Create User Operation", response = ResponseEntity.class)
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO user) {
        return userService.save(user);
    }

    @GetMapping("/getByTeam/{teamId}") // /api/user/getByTeam/{teamId}
    @ApiOperation(value = "Get Users By TeamId", response = List.class)
    public List<UserDTO> getUsersByTeamId(@PathVariable Long teamId) {
        return userService.getUsersByTeamId(teamId);
    }

}
