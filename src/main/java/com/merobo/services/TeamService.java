package com.merobo.services;

import com.merobo.beans.TeamBean;
import com.merobo.beans.UserBean;
import com.merobo.dtos.TeamTo;
import com.merobo.repositories.TeamRepository;
import com.merobo.repositories.UserRepository;
import com.merobo.utils.DtoCreatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    public void updateTeam(TeamTo teamTo) {
        TeamBean teamBean = teamRepository.findOne(teamTo.getId());
        System.out.print(teamBean);
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

    public List<TeamTo> getAllTeams() {
        List<TeamTo> teamTos = teamRepository
                .findAll()
                .stream()
                .flatMap(teamBean ->
                        Stream.of(DtoCreatorUtil.createTeamTo(teamBean))
                ).collect(Collectors.toList());
        return teamTos;
    }

    public void deleteTeam(TeamTo teamTo) {
        teamRepository.deleteByName(teamTo.getName());
    }

}
