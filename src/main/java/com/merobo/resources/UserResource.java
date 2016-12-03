package com.merobo.resources;

import com.merobo.dtos.PasswordDto;
import com.merobo.dtos.UserTo;
import com.merobo.exceptions.UserNotFoundException;
import com.merobo.exceptions.UserServiceException;
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

    @POST
    @Path("encodeall")
    public Response encodeAllPasswords() {
        userService.encodeAll();
        return Response.ok().build();
    }

    @PUT
    @Path("{username}/resetpassword")
    public Response resetPassword(@PathParam("username") String username) {
        Response response = null;
        try {
            String newPwd = userService.resetPassword(username);
            response = Response.ok(new PasswordDto(newPwd)).build();
        } catch (UserNotFoundException e) {
            response = Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            response = Response.serverError().entity(e.getMessage()).build();
        }
        return response;
    }

    @PUT
    @Path("{username}")
    public Response changePassword(@PathParam("username") String username, UserTo userTo) {
        Response response = null;
        try {
            userTo.setUsername(username);
            userService.update(userTo);
            response = Response.noContent().build();
        } catch (UserServiceException e) {
            response = Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
        return response;
    }
}
