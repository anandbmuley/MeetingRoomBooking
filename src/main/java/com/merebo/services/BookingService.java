package com.merebo.services;

import java.util.List;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.merobo.beans.BookingBean;
import com.merobo.dtos.BookingTo;
import com.merobo.repositories.BookingRepositories;

@Service
public class BookingService {
	@Autowired
	BookingRepositories bookingRepositories; 
	public Response bookRoom(BookingTo bookingTo) {
	
	    BookingBean bookingBean = new BookingBean();
	    bookingBean.setTeam(bookingTo.getTeam());
	    bookingBean.setDate(bookingTo.getDate());
	    bookingBean.setStartTime(bookingTo.getStartTime());
	    bookingBean.setEndTime(bookingTo.getEndTime());
		
		bookingRepositories.save(bookingBean);
		return Response.ok(bookingTo).build();

	}

	public Response getAllTeamBookings() {
		List<BookingBean> teams = bookingRepositories.findAll();
		return Response.ok(teams).build();

	}
	
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
	
	
	public Response findTeamBooking(@QueryParam("team") String name) {
		BookingBean bookingBean = bookingRepositories.findByTeam(name);
		if (bookingBean != null) {
			return Response.ok(bookingBean).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	
	

}
