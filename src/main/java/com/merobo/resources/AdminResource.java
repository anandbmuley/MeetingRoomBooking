package com.merobo.resources;

import com.merobo.dtos.TeamTo;
import com.merobo.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Component
@Path("admin")
public class AdminResource {

    @Autowired
    private TeamService teamService;

    @PUT
    @Path("team/{id}")
    public Response updateTeam(@PathParam("id") String id, TeamTo teamTo) {
        teamTo.setId(id);
        teamService.updateTeam(teamTo);
        return Response.ok().build();
    }

}
