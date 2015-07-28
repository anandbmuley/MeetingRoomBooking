package com.merobo.resources;

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
import com.merobo.repositories.BookingRepositories;

@Component
@Path("booking")
public class BookingResource {
	
	@Autowired
	private BookingRepositories bookingRepositories;

	@POST
	@Path("bookroom")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response bookRoom(BookingTo bookingTo) {
		System.out.println("hi");
		BookingBean entity = new BookingBean();
		entity.setTeam(bookingTo.getTeam());
		System.out.println(entity.getTeam());
		System.out.println("hello");
		entity.setDate(bookingTo.getDate());
		System.out.println(entity.getDate());
		entity.setStartTime(bookingTo.getStartTime());
		entity.setEndTime(bookingTo.getEndTime());

		bookingRepositories.save(entity);
		return Response.ok("Team " + entity.getTeam() + " booked").build();

	}

	@GET
	@Path("list")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response getAllTeamBookings() {
		List<BookingBean> teams = bookingRepositories.findAll();
		return Response.ok(teams).build();

	}

	@DELETE
	@Path("delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteTeam(BookingTo teamTo) {
		List<BookingBean> s = bookingRepositories
				.deleteByTeam(teamTo.getTeam());
		System.out.println(s);
		if (!s.isEmpty())
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
		BookingBean bookingBean = bookingRepositories.findByTeam(name);
		if (bookingBean != null) {
			return Response.ok(bookingBean).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

}
