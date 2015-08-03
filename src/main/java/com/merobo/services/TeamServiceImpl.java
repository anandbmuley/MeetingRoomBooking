package com.merobo.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.merobo.beans.TeamBean;
import com.merobo.dtos.TeamTo;
import com.merobo.repositories.TeamRepository;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamRepository teamRepository;

	public TeamTo addTeam(TeamTo teamTo) {
		TeamBean teamBean = new TeamBean();
		teamBean.setName(teamTo.getName());
		teamBean.setCity(teamTo.getCity());
		teamBean = teamRepository.save(teamBean);
		teamTo.setName(teamBean.getName());
		teamTo.setCity(teamBean.getCity());
		return teamTo;
	}

	public List<TeamBean> getAllTeam() {
		List<TeamBean> teams = teamRepository.findAll();
		return teams;

	}

	public List<TeamBean> deleteTeam(TeamTo teamTo) {
		List<TeamBean> list = teamRepository.deleteByName(teamTo.getName());
		System.out.println(list);
		if (!list.isEmpty())
			return list;
		else
			return null;

	}

	@GET
	@Path("find")
	@Produces(MediaType.APPLICATION_JSON)
	public TeamTo findTeam(@QueryParam("name") String name) {
		TeamBean teamBean = teamRepository.findByName(name);
		System.out.println(teamBean);
		if (teamBean != null) {
			TeamTo teamTo = new TeamTo();
			teamTo.setName(teamBean.getName());
			teamTo.setCity(teamBean.getCity());
			return teamTo;
		} else
			return null;

	}

}
