package com.merobo.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.merobo.dtos.TeamTo;
import com.merobo.services.TeamService;

@Component
@Path("team")
public class TeamResource {

	@Autowired
	private TeamService teamService;

	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addTeam(TeamTo teamTo) {
		teamTo = teamService.addTeam(teamTo);
		return Response.status(Response.Status.CREATED).build();
	}

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllTeam() {
		List<TeamTo> teams = teamService.getAllTeams();
		return Response.ok(teams).build();

	}

	@DELETE
	@Path("delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteTeam(TeamTo teamTo) {
		teamService.deleteTeam(teamTo);
		return Response.ok().build();
	}

}
