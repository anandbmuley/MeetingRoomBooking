package com.merobo.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.merobo.dtos.UserTo;
import com.merobo.exceptions.UserServiceException;
import com.merobo.services.UserService;

@Component
@Path("authentication")
public class AuthenticationResource {

	@Autowired
	private UserService userService;

	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(UserTo userTo) {
		Response response = null;
		try {
			userService.login(userTo);
			userTo.setPassword("");
			response = Response.ok(userTo).build();
		} catch (UserServiceException e) {
			response = Response.status(Response.Status.BAD_REQUEST)
					.entity(e.getMessage()).build();
		}
		return response;
	}

	@POST
	@Path("create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(UserTo userTo) {
		Response response = null;
		try {
			userService.create(userTo);
			response = Response.status(Response.Status.CREATED).build();
		} catch (UserServiceException e) {
			response = Response.status(Response.Status.CONFLICT)
					.entity(e.getMessage()).build();
		}

		return response;
	}

}