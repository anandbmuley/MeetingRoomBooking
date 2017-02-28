package com.merobo.resources;

import com.merobo.dtos.TeamTo;
import com.merobo.exceptions.NoDataFoundException;
import com.merobo.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("team")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
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
    public Response getAllTeams() {
        List<TeamTo> teams = teamService.getAllTeams();
        return Response.ok(teams).build();
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<TeamTo> teams = teamService.getAll();
        return Response.ok(teams).build();
    }

    @GET
    @Path("list/members")
    public Response getAllTeam() {
        List<TeamTo> teams = teamService.getAllTeams();
        return Response.ok(teams).build();
    }

    @GET
    @Path("list")
    public Response teamList() {
        try {
            List<TeamTo> teamList = teamService.getTeamList();
            return Response.ok(teamList).build();
        } catch (NoDataFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
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
