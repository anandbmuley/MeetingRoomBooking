package com.merobo.resources;

import java.util.HashSet;
import java.util.Iterator;
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
import com.merobo.repositories.TeamRepository;

@Component
@Path("team")
public class TeamResource {

	@Autowired
	private TeamRepository teamRepository;

	private static Set<String> l1 = new HashSet<String>();

	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addTeam(TeamTo teamTo) {
		TeamBean teamBean = new TeamBean();
		teamBean.setName(teamTo.getName());
		teamBean.setCity(teamTo.getCity());
		teamRepository.save(teamBean);
		return Response.ok("Team " + teamTo.getName()+" from  "+teamBean.getCity() + " Added").build();
	}

	@GET
	@Path("list")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response getAllTeam() {
		List<TeamBean> teams = teamRepository.findAll();
		return Response.ok(teams).build();

	}

	@DELETE
	@Path("delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteTeam(TeamTo teamTo) {
		List<TeamBean> s= teamRepository.deleteByName(teamTo.getName());
		System.out.println(s);
		if(!s.isEmpty())
		return Response.ok("team " + teamTo.getName() + " Deleted").build();
		else 
			return Response.ok("team " + teamTo.getName() + " not Deleted because it is not available").build();

	}

	@GET
	@Path("find1")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findTeam1(@QueryParam("name") String name) {
		Iterator<String> it = l1.iterator();
		while (it.hasNext()) {
			String s = (String) it.next();
			if (s.equalsIgnoreCase(name)) {
				return Response.ok("present").build();
			}
		}
		return Response.ok("not present").build();

	}

	@GET
	@Path("find")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findTeam(@QueryParam("name") String name) {
		TeamBean s=(TeamBean) teamRepository.findByName(name);
		System.out.println(s);
		if(s !=null)
			return Response.ok("present").build();
		else
		return Response.ok("not present").build();

	}

}
