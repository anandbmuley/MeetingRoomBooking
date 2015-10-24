package com.merobo.resources;

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

import com.merobo.dtos.BookingTo;
import com.merobo.exceptions.BookingServiceException;
import com.merobo.services.BookingService;

@Component
@Path("booking")
public class BookingResource {

	@Autowired
	private BookingService bookingService;

	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response bookRoom(BookingTo bookingTo) {
		Response response = null;
		try {
			bookingService.bookRoom(bookingTo);
			response = Response.ok(bookingTo.getId()).build();
		} catch (BookingServiceException e) {
			response = Response.status(Response.Status.BAD_REQUEST)
					.entity(e.getCause().getMessage()).build();
		}
		return response;
	}

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllBookings() {
		return Response.ok(bookingService.getAll()).build();
	}

	@DELETE
	@Path("cancel")
	@Produces(MediaType.APPLICATION_JSON)
	public Response cancelBooking(@QueryParam("bid") String bookingId) {
		bookingService.cancelBooking(bookingId);
		return Response.ok().build();
	}

}
