package com.merobo.dtos;

public class BookingTo {

	private String id;
	private String teamName;
	private String startTime;
	private String endTime;
	private String bookedBy;
	private String bookedWhen;
	private String roomName;

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

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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

	@Override
	public String toString() {
		return "BookingTo [id=" + id + ", teamName=" + teamName
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", bookedBy=" + bookedBy + ", bookedWhen=" + bookedWhen
				+ ", roomName=" + roomName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bookedBy == null) ? 0 : bookedBy.hashCode());
		result = prime * result
				+ ((bookedWhen == null) ? 0 : bookedWhen.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((roomName == null) ? 0 : roomName.hashCode());
		result = prime * result
				+ ((startTime == null) ? 0 : startTime.hashCode());
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
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (teamName == null) {
			if (other.teamName != null)
				return false;
		} else if (!teamName.equals(other.teamName))
			return false;
		return true;
	}

}
