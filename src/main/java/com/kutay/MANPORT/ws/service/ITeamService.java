package com.kutay.MANPORT.ws.service;

import com.kutay.MANPORT.ws.dto.TeamDTO;

import java.util.List;

public interface ITeamService {
    List<TeamDTO> getAllTeams();

    TeamDTO getById(Long id);
}
