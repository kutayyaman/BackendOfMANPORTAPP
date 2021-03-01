package com.kutay.MANPORT.ws.api;

import com.kutay.MANPORT.ws.domain.Country;
import com.kutay.MANPORT.ws.dto.ServerDTO;
import com.kutay.MANPORT.ws.dto.TeamDTO;
import com.kutay.MANPORT.ws.service.ITeamService;
import com.kutay.MANPORT.ws.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.TeamCtrl.CTRL) // /api/team
@Api(value = ApiPaths.TeamCtrl.CTRL, description = "Team APIs")
public class TeamController {
    private final ITeamService teamService;

    public TeamController(ITeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping() // /api/team
    @ApiOperation(value = "Get All Teams", response = List.class)
    public List<TeamDTO> getAllTeams() {
        return teamService.getAllTeams();
    }
}
