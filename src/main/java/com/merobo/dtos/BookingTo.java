package com.merobo.dtos;

import java.text.ParseException;
import java.util.Date;

import com.merobo.utils.BookingStatus;
import com.merobo.utils.DateConverterUtil;

public class BookingTo implements Comparable<BookingTo> {

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) throws ParseException {
		this.startTime = startTime;
		this.startDateTime = DateConverterUtil.toDate(startTime,
				DateConverterUtil.PATTERN_HH_MM_MERIDIAN);
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) throws ParseException {
		this.endTime = endTime;
		this.endDateTime = DateConverterUtil.toDate(endTime,
				DateConverterUtil.PATTERN_HH_MM_MERIDIAN);
	}

	public String getBookedBy() {
		return bookedBy;
	}

	public void setBookedBy(String bookedBy) {
		this.bookedBy = bookedBy;
	}

	public String getBookedWhen() {
		return bookedWhen;
	}

	public void setBookedWhen(String bookedWhen) {
		this.bookedWhen = bookedWhen;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public Date getEndDateTime() {
		return endDateTime;
	}

	@Override
	public String toString() {
		return "BookingTo [id=" + id + ", teamName=" + teamName
				+ ", startTime=" + startTime + ", startDateTime="
				+ startDateTime + ", endTime=" + endTime + ", endDateTime="
				+ endDateTime + ", bookedBy=" + bookedBy + ", bookedWhen="
				+ bookedWhen + ", roomName=" + roomName + ", status=" + status
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bookedBy == null) ? 0 : bookedBy.hashCode());
		result = prime * result
				+ ((bookedWhen == null) ? 0 : bookedWhen.hashCode());
		result = prime * result
				+ ((endDateTime == null) ? 0 : endDateTime.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((roomName == null) ? 0 : roomName.hashCode());
		result = prime * result
				+ ((startDateTime == null) ? 0 : startDateTime.hashCode());
		result = prime * result
				+ ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((teamName == null) ? 0 : teamName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookingTo other = (BookingTo) obj;
		if (bookedBy == null) {
			if (other.bookedBy != null)
				return false;
		} else if (!bookedBy.equals(other.bookedBy))
			return false;
		if (bookedWhen == null) {
			if (other.bookedWhen != null)
				return false;
		} else if (!bookedWhen.equals(other.bookedWhen))
			return false;
		if (endDateTime == null) {
			if (other.endDateTime != null)
				return false;
		} else if (!endDateTime.equals(other.endDateTime))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (roomName == null) {
			if (other.roomName != null)
				return false;
		} else if (!roomName.equals(other.roomName))
			return false;
		if (startDateTime == null) {
			if (other.startDateTime != null)
				return false;
		} else if (!startDateTime.equals(other.startDateTime))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (status != other.status)
			return false;
		if (teamName == null) {
			if (other.teamName != null)
				return false;
		} else if (!teamName.equals(other.teamName))
			return false;
		return true;
	}

	@Override
	public int compareTo(BookingTo o) {
		return startDateTime.compareTo(o.getStartDateTime());
	}

}
