package com.merobo.resources;

import java.util.ArrayList;
import java.util.List;

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

import com.merobo.beans.BookingBean;
import com.merobo.dtos.BookingTo;
import com.merobo.services.BookingService;
import com.merobo.services.BookingServiceImpl;

@Component
@Path("booking")
public class BookingResource {

	@Autowired
	private BookingService bookingService;

	BookingBean bookingBean = new BookingBean();
	List<BookingBean> list = new ArrayList<BookingBean>();

	// BookingTo bookingTo =new BookingTo();

	@POST
	@Path("bookroom")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response bookRoom(BookingTo bookingTo) {
		BookingTo bookingTo2 = new BookingTo();
		bookingTo2 = bookingService.bookRoom(bookingTo);
		return Response.ok(bookingTo2).build();

	}

	@GET
	@Path("list")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response getAllTeamBookings() {
		List<BookingBean> teams = bookingService.findAll();
		return Response.ok(teams).build();

	}

	@DELETE
	@Path("delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteTeam(BookingTo teamTo) {
		List<BookingBean> list = bookingService.deleteTeam(teamTo.getTeam());
		System.out.println(list);
		if (!list.isEmpty())
			return Response.ok(
					"team " + teamTo.getTeam() + "booking is  Deleted").build();
		else
			return Response
					.ok("team "
							+ teamTo.getTeam()
							+ " booking does not Deleted because it is not available")
					.build();

	}

	@GET
	@Path("find")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findTeamBooking(@QueryParam("team") String name) {
		System.out.println(name);
		BookingTo bookingTo = bookingService.findTeamBooking(name);
		System.out.println(bookingTo);
		if (bookingTo != null) {
			return Response.ok(bookingTo).build();
		} else {
			return Response.ok().status(Response.Status.NOT_FOUND).build();
			// return Response.ok(" "+ bookingBean+"not available" ).build();
		}
	}

}
