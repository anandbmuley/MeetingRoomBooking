package com.merobo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.merobo.beans.MemberBean;
import com.merobo.beans.TeamBean;
import com.merobo.dtos.TeamTo;
import com.merobo.repositories.TeamRepository;
import com.merobo.utils.DtoCreatorUtil;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamRepository teamRepository;

	@Override
	public TeamTo addTeam(TeamTo teamTo) {
		TeamBean teamBean = new TeamBean();
		teamBean.setName(teamTo.getName());
		teamRepository.save(teamBean);
		teamTo.setId(teamBean.getId());
		return teamTo;
	}

	@Override
	public List<TeamTo> getAllTeams() {
		List<TeamBean> teams = teamRepository.findAll();
		return DtoCreatorUtil.createTeamTos(teams);
	}

	@Override
	public void deleteTeam(TeamTo teamTo) {
		teamRepository.deleteByName(teamTo.getName());
	}

	@Override
	public TeamTo findTeam(String name) {
		TeamBean teamBean = teamRepository.findByName(name);
		return DtoCreatorUtil.createTeamTo(teamBean);
	}

	@Override
	public TeamTo addMember(String teamId, String memberName) {
		TeamBean teamBean = teamRepository.findOne(teamId);
		teamBean.getMemberBeans().add(new MemberBean(memberName));
		teamRepository.save(teamBean);
		return DtoCreatorUtil.createTeamTo(teamBean);
	}

}
