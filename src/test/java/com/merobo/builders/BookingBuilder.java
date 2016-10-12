package com.merobo.builders;

import java.text.ParseException;
import java.util.Date;

import com.merobo.dtos.BookingTo;
import com.merobo.utils.BookingStatus;

public class BookingBuilder {

	private String id;
	private String teamName;
	private String startTime;
	private Date startDateTime;
	private String endTime;
	private Date endDateTime;
	private String bookedBy;
	private String bookedWhen;
	private String roomName;
	private BookingStatus status;

	public BookingBuilder() {

	}

	public BookingBuilder setStartTime(String startTime) {
		this.startTime = startTime;
		return this;
	}

	public BookingBuilder setBookedWhen(String bookedWhen) {
		this.bookedWhen = bookedWhen;
		return this;
	}

	public BookingBuilder setEndTime(String endTime) {
		this.endTime = endTime;
		return this;
	}

	public BookingTo build() throws ParseException {
		BookingTo bookingTo = new BookingTo();
		bookingTo.setBookedBy(bookedBy);
		bookingTo.setBookedWhen(bookedWhen);
		bookingTo.setEndTime(endTime);
		bookingTo.setId(id);
		bookingTo.setRoomName(roomName);
		bookingTo.setStartTime(startTime);
		bookingTo.setStatus(status);
		bookingTo.setTeamName(teamName);
		return bookingTo;
	}

}
