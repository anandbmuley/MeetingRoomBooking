package com.merobo.services;

import com.merobo.beans.TeamBean;
import com.merobo.beans.UserBean;
import com.merobo.dtos.TeamTo;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    public void updateTeam(TeamTo teamTo) {
        TeamBean teamBean = teamRepository.findOne(teamTo.getId());
        String oldTeamName = teamBean.getName();
        teamBean.setName(teamTo.getName());
        teamRepository.save(teamBean);
        List<UserBean> users = userRepository.findByTeamName(oldTeamName);
        users.forEach(userBean -> userBean.setTeamName(teamTo.getName()));
        userRepository.save(users);
    }

    public TeamTo addTeam(TeamTo teamTo) {
        TeamBean teamBean = new TeamBean();
        teamBean.setName(teamTo.getName());
        teamRepository.save(teamBean);
        teamTo.setId(teamBean.getId());
        return teamTo;
    }

    public List<TeamTo> getTeamList() throws NoDataFoundException {
        List<TeamBean> teams = teamRepository.findAll();
        if(CollectionUtils.isEmpty(teams)){
            throw new NoDataFoundException("No teams found");
        }
        return teams.stream().map(DtoCreatorUtil::createTeamTo).collect(Collectors.toList());
    }

    public List<TeamTo> getAll() {
        List<TeamTo> teamTos = Collections.emptyList();
        List<TeamBean> teams = teamRepository.findAll();
        if (!CollectionUtils.isEmpty(teams)) {
            teamTos = teams.stream().map(DtoCreatorUtil::createTeamTo).collect(Collectors.toList());
        }
        return teamTos;
    }

    public List<TeamTo> getAllTeams() {
        List<UserBean> members = userRepository.findAll();
        List<TeamTo> teamTos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(members)) {
            List<String> teamNames = members.stream().map(UserBean::getTeamName).collect(Collectors.toList());
            List<TeamBean> teams = teamRepository.findByNameIn(teamNames);
            if (!CollectionUtils.isEmpty(teams)) {
                teams.stream().forEach(teamBean -> {
                    List<UserBean> teamMembers = members.stream().filter(userBean -> teamBean.getName().equals(userBean.getTeamName())).collect(Collectors.toList());
                    teamBean.getMembers().addAll(teamMembers);
                    teamTos.add(DtoCreatorUtil.createTeamTo(teamBean));
                });
            }
        }

        return teamTos;
    }

    public void deleteTeam(TeamTo teamTo) {
        teamRepository.deleteByName(teamTo.getName());
    }

}
