package com.merobo.beans;

import java.util.Date;

import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.merobo.utils.BookingStatus;

@Document(collection = "bookings")
public class BookingBean {

	@Id
	private String id;
	private String teamName;
	private Date startTime;
	private Date endTime;
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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
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

	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BookingBean [id=" + id + ", teamName=" + teamName
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", bookedBy=" + bookedBy + ", bookedWhen=" + bookedWhen
				+ ", roomName=" + roomName + ", status=" + status + "]";
	}

	public String toJSON() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("teamName", teamName);
		jsonObject.put("startTime", startTime);
		jsonObject.put("endTime", endTime);
		jsonObject.put("bookedBy", bookedBy);
		jsonObject.put("bookedWhen", bookedWhen);
		jsonObject.put("roomName", roomName);
		jsonObject.put("status", status);
		return jsonObject.toString();
	}

}
