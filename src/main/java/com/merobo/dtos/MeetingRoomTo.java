package com.merobo.dtos;

import java.util.List;

public class MeetingRoomTo {

	private List<BookingTo> pinnacle;
	private List<BookingTo> other;

	public List<BookingTo> getPinnacle() {
		return pinnacle;
	}

	public void setPinnacle(List<BookingTo> pinnacle) {
		this.pinnacle = pinnacle;
	}

	public List<BookingTo> getOther() {
		return other;
	}

	public void setOther(List<BookingTo> other) {
		this.other = other;
	}

}
