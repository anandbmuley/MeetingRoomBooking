package com.merobo.resources;

import com.merobo.exceptions.UserNotFoundException;
import com.merobo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Autowired
    private UserService userService;

    @PUT
    @Path("{username}/resetpassword")
    public Response resetPassword(@PathParam("username") String username) {
        Response response = null;
        try {
            userService.resetPassword(username);
            response = Response.noContent().build();
        } catch (UserNotFoundException e) {
            response = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            response = Response.serverError().entity(e.getMessage()).build();
        }
        return response;
    }

    @PUT
    @Path("{username}/password/{newpassword}")
    public Response changePassword(@PathParam("username") String username, @PathParam("newpassword") String newpassword) {
        Response response = Response.ok().build();
        return response;
    }
}
