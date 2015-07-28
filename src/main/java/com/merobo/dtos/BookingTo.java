package com.merobo.dtos;

import java.util.Date;

import javax.ws.rs.QueryParam;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingTo {

	
	@JsonProperty
	private String team;
	private Date date = new Date();
	private String startTime;
	private String endTime;

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	@Override
	public String toString() {
		return "BookingTo [team=" + team + ", date=" + date + ", startTime="
				+ startTime + ", endTime=" + endTime + "]";
	}

}
