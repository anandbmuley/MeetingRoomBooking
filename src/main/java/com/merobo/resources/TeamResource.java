package com.merobo.resources;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.merobo.beans.TeamBean;
import com.merobo.dtos.TeamTo;
import com.merobo.services.TeamService;

@Component
@Path("team")
public class TeamResource {

	@Autowired
	private TeamService teamService;

	private static Set<String> l1 = new HashSet<String>();
	TeamTo teamTo = new TeamTo();

	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addTeam(TeamTo teamTo) {

		teamTo =teamService.addTeam(teamTo);
		return Response.ok(
				"Team " + teamTo.getName() + " from  " + teamTo.getCity()
						+ " Added").build();
	}

	@GET
	@Path("list")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response getAllTeam() {
		List<TeamBean> teams = teamService.getAllTeam();
		return Response.ok(teams).build();

	}

	@DELETE
	@Path("delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteTeam(TeamTo teamTo) {
		List<TeamBean> list = teamService.deleteTeam(teamTo);
		System.out.println(list);
		if (!(list == null))
			return Response.ok("team " + teamTo.getName() + " Deleted").build();
		else
			return Response.ok(
					"team " + teamTo.getName()
							+ " not Deleted because it is not available")
					.build();

	}


	@GET
	@Path("find")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findTeam(@QueryParam("name") String name) {
		TeamTo teamTo =  teamService.findTeam(name);
		//System.out.println(teamTo);
		if (teamTo != null)
			return Response.ok(teamTo).build();
		else
			return Response.ok().status(Response.Status.NOT_FOUND).build();

	}

}
