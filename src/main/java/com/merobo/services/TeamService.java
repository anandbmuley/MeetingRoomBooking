package com.merobo.services;

import abm.authenticator.repositories.UserRepository;
import com.merobo.beans.Team;
import com.merobo.dtos.TeamDto;
import com.merobo.exceptions.NoDataFoundException;
import com.merobo.repositories.TeamRepository;
import com.merobo.utils.DtoCreatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository, UserRepository userRepository) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }

    public TeamDto addTeam(TeamDto teamDto) {
        Team team = new Team();
        team.setName(teamDto.getName());
        teamRepository.save(team);
        teamDto.setId(team.getId());
        return teamDto;
    }

    public List<TeamDto> getTeamList() throws NoDataFoundException {
        List<Team> teams = teamRepository.findAll();
        if (CollectionUtils.isEmpty(teams)) {
            throw new NoDataFoundException("No teams found");
        }
        return teams.stream().map(DtoCreatorUtil::createTeamTo).collect(Collectors.toList());
    }

    public List<TeamDto> getAll() {
        List<TeamDto> teamDtos = Collections.emptyList();
        List<Team> teams = teamRepository.findAll();
        if (!CollectionUtils.isEmpty(teams)) {
            teamDtos = teams.stream().map(DtoCreatorUtil::createTeamTo).collect(Collectors.toList());
        }
        return teamDtos;
    }

    public void deleteTeam(TeamDto teamDto) {
        teamRepository.deleteByName(teamDto.getName());
    }

}
