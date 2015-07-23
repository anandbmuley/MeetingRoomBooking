package com.merobo.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.context.annotation.ComponentScan.Filter;

@Path("team")
public class TeamResource {

	private static List<String> l1 = new ArrayList<String>(5);

	@GET
	@Path("add")
	public String addTeam(@QueryParam("name") String name) {
		// List<String> l1= new ArrayList<String>();
		// l1.add("akshay");
		l1.add(name);
		return "Team "+name+" Added";
	}

	@GET
	@Path("list")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response getAllTeam() {
		return Response.ok(l1).build();

	}

	@DELETE
	@Path("delete")
	public String deleteTeam(@QueryParam("name") String name) {
		l1.remove(name);
		return "team " + name + " Deleted";

	}

	@GET
	@Path("find")
	public String findTeam(@QueryParam("name") String name) {
		for (int i = 0; i < l1.size(); i++) {
			String s = l1.get(i);
			if (s.equalsIgnoreCase(name)) {
				return "present";
			}
		}

		return "not present";

	}

}
