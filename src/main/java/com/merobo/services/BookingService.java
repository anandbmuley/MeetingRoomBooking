package com.merobo.services;

import java.util.List;

import com.merobo.beans.BookingBean;
import com.merobo.dtos.BookingTo;

public interface BookingService {

	
	public BookingTo bookRoom(BookingTo bookingTo) ;
	//public BookingBean save(BookingBean bookingBean);

	public List<BookingBean> findAll();

	public BookingTo findTeamBooking(String with);

	public List<BookingBean> deleteTeam(String team);

	
}
