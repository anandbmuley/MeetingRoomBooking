package com.merobo.resources;

import com.merobo.dtos.UserTo;
import com.merobo.exceptions.UserServiceException;
import com.merobo.services.UserService;
import com.merobo.utils.DateConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

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
			userTo.setLoginTime(DateConverterUtil.toString(new Date(), DateConverterUtil.PATTERN_HH_MM_MERIDIAN));
			response = Response.ok(userTo).build();
		} catch (UserServiceException e) {
			response = Response.status(Response.Status.BAD_REQUEST).entity("Authentication Failed !").build();
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
			response = Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
		}

		return response;
	}

}