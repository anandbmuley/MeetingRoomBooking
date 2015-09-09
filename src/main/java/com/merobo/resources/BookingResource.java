package com.merobo.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.merobo.dtos.BookingTo;
import com.merobo.dtos.MeetingRoomTo;
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
		bookingService.bookRoom(bookingTo);
		return Response.ok(bookingTo.getId()).build();
	}

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllBookings() {
		List<BookingTo> bookingTos = bookingService.getAll();
		MeetingRoomTo meetingRoomTo = new MeetingRoomTo();
		meetingRoomTo.setPinnacle(bookingTos);
		return Response.ok(meetingRoomTo).build();
	}

}
