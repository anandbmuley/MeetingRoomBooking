package com.merobo.resources;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.merobo.dtos.TeamTo;
import com.merobo.services.TeamService;

@Component
@Path("admin")
public class AdminResource {

	@Autowired
	private TeamService teamService;

	@PUT
	@Path("team/{id}")
	public void update(@PathParam("id") String id,TeamTo teamTo) {
		teamTo.setId(id);
		teamService.updateTeam(teamTo);
	}

}
