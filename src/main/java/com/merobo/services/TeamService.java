package com.merobo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.merobo.beans.TeamBean;
import com.merobo.beans.UserBean;
import com.merobo.dtos.TeamTo;
import com.merobo.repositories.TeamRepository;
import com.merobo.repositories.UserRepository;
import com.merobo.utils.DtoCreatorUtil;

@Service
public class TeamService {

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private UserRepository userRepository;

	public TeamTo addTeam(TeamTo teamTo) {
		TeamBean teamBean = new TeamBean();
		teamBean.setName(teamTo.getName());
		teamRepository.save(teamBean);
		teamTo.setId(teamBean.getId());
		return teamTo;
	}

	public List<TeamTo> getAllTeams() {
		List<TeamBean> teams = teamRepository.findAll();
		List<TeamTo> teamTos = DtoCreatorUtil.createTeamTos(teams);
		for (TeamTo teamTo : teamTos) {
			List<UserBean> users = userRepository.findByTeamName(teamTo
					.getName());
			teamTo.getMemberTos().addAll(DtoCreatorUtil.createUserTos(users));
		}
		return teamTos;
	}

	public void deleteTeam(TeamTo teamTo) {
		teamRepository.deleteByName(teamTo.getName());
	}

}
