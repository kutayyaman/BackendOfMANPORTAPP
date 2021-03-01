package com.kutay.MANPORT.ws.service.Impl;

import com.kutay.MANPORT.ws.domain.RowStatus;
import com.kutay.MANPORT.ws.domain.Team;
import com.kutay.MANPORT.ws.dto.TeamDTO;
import com.kutay.MANPORT.ws.repository.TeamRepository;
import com.kutay.MANPORT.ws.service.ITeamService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl implements ITeamService {
    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<TeamDTO> getAllTeams() {
        List<Team> teams=teamRepository.findAllByRowStatus(RowStatus.ACTIVE);
        List<TeamDTO> teamDTOS = new ArrayList<>();
        for (Team team:teams){
            TeamDTO teamDTO = new TeamDTO(team);
            teamDTOS.add(teamDTO);
        }
        return teamDTOS;
    }

    @Override
    public TeamDTO getById(Long id) {
        TeamDTO teamDTO = new TeamDTO(teamRepository.findFirstByIdAndRowStatus(id,RowStatus.ACTIVE));
        return teamDTO;
    }
}
