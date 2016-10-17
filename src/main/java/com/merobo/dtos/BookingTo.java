package com.merobo.dtos;

import com.merobo.utils.BookingStatus;
import com.merobo.utils.DateConverterUtil;

import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingTo bookingTo = (BookingTo) o;
        return Objects.equals(id, bookingTo.id) &&
                Objects.equals(teamName, bookingTo.teamName) &&
                Objects.equals(startTime, bookingTo.startTime) &&
                Objects.equals(startDateTime, bookingTo.startDateTime) &&
                Objects.equals(endTime, bookingTo.endTime) &&
                Objects.equals(endDateTime, bookingTo.endDateTime) &&
                Objects.equals(bookedBy, bookingTo.bookedBy) &&
                Objects.equals(bookedWhen, bookingTo.bookedWhen) &&
                Objects.equals(roomName, bookingTo.roomName) &&
                status == bookingTo.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teamName, startTime, startDateTime, endTime, endDateTime, bookedBy, bookedWhen, roomName, status);
    }

    @Override
    public int compareTo(BookingTo o) {
        return startDateTime.compareTo(o.getStartDateTime());
    }

}
