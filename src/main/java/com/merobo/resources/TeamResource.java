package com.merobo.resources;

import java.util.HashSet;
import java.util.Iterator;
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

import org.springframework.http.HttpStatus;

import com.merobo.models.Team;

@Path("team")
public class TeamResource {

	private static Set<String> l1 = new HashSet<String>();

	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addTeam(Team team) {
		l1.add(team.getName());
		return Response.ok("Team " + team.getName() + " Added").build();
	}

	@GET
	@Path("list")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response getAllTeam() {
		return Response.ok(l1).build();

	}

	@DELETE
	@Path("delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteTeam(Team team) {
		l1.remove(team.getName());
		return Response.ok("team " + team.getName() + " Deleted").build();

	}

	@GET
	@Path("find")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findTeam(@QueryParam("name") String name) {
		Iterator it = l1.iterator();
		while (it.hasNext()) {
			String s = (String) it.next();
			if (s.equalsIgnoreCase(name)) {
				return Response.ok("present").build();
			}
		}
		return Response.ok("not present").build();

	}

}
