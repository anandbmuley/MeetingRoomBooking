package com.merobo.dtos;

import java.util.ArrayList;
import java.util.List;

public class MeetingRoomTo {

	private List<BookingDto> pinnacle = new ArrayList<BookingDto>();
	private List<BookingDto> other = new ArrayList<BookingDto>();

	public List<BookingDto> getPinnacle() {
		return pinnacle;
	}

	public List<BookingDto> getOther() {
		return other;
	}

	@Override
	public String toString() {
		return "MeetingRoomTo [pinnacle=" + pinnacle + ", other=" + other + "]";
	}

}
