package com.merobo.services;

import com.merobo.beans.Team;
import com.merobo.beans.User;
import com.merobo.dtos.TeamDto;
import com.merobo.exceptions.NoDataFoundException;
import com.merobo.repositories.TeamRepository;
import com.merobo.repositories.UserRepository;
import com.merobo.utils.DtoCreatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    public void updateTeam(TeamDto teamDto) {
        teamRepository.findById(teamDto.getId())
                .ifPresent($ -> {
                    List<User> users = userRepository.findByTeamName($.getName());
                    users.forEach(userBean -> userBean.setTeamName(teamDto.getName()));
                    userRepository.saveAll(users);
                    $.setName(teamDto.getName());
                    teamRepository.save($);
                });
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

    public List<TeamDto> getAllTeams() {
        List<User> members = userRepository.findAll();
        List<TeamDto> teamDtos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(members)) {
            List<String> teamNames = members.stream().map(User::getTeamName).collect(Collectors.toList());
            List<Team> teams = teamRepository.findByNameIn(teamNames);
            if (!CollectionUtils.isEmpty(teams)) {
                teams.stream().forEach(team -> {
                    List<User> teamMembers = members.stream().filter(userBean -> team.getName().equals(userBean.getTeamName())).collect(Collectors.toList());
                    team.getMembers().addAll(teamMembers);
                    teamDtos.add(DtoCreatorUtil.createTeamTo(team));
                });
            }
        }

        return teamDtos;
    }

    public void deleteTeam(TeamDto teamDto) {
        teamRepository.deleteByName(teamDto.getName());
    }

}
