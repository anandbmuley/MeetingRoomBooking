package com.merobo.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.merobo.utils.BookingStatus;

import java.time.LocalDateTime;

import static abm.commons.DateConverterUtil.formatTime;
import static abm.commons.DateConverterUtil.parseTime;

public class BookingDto {

    private String id;
    private String startTime;
    private LocalDateTime startDateTime;

    private String endTime;
    private LocalDateTime endDateTime;

    private String bookedById;
    private String roomId;
    private BookingStatus status;
    private boolean bookedByMe;

    @JsonCreator
    public BookingDto(
            @JsonProperty("startTime") String startTime,
            @JsonProperty("endTime") String endTime,
            @JsonProperty("bookedById") String bookedById) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.bookedById = bookedById;
        this.startDateTime = parseTime(startTime);
        this.endDateTime = parseTime(endTime);
    }

    public BookingDto(String id, LocalDateTime startDateTime, LocalDateTime endDateTime, String bookedById, String roomId, BookingStatus status) {
        this.id = id;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.bookedById = bookedById;
        this.roomId = roomId;
        this.status = status;
        this.startTime = formatTime(startDateTime);
        this.endTime = formatTime(endDateTime);
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getId() {
        return id;
    }

    public String getStartTime() {
        return startTime;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public String getBookedById() {
        return bookedById;
    }

    public String getRoomId() {
        return roomId;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setBookedByMe(boolean bookedByMe) {
        this.bookedByMe = bookedByMe;
    }

    public boolean isBookedByMe() {
        return bookedByMe;
    }

    public void roomBooked(String id) {
        startTime = null;
        endTime = null;
        startDateTime = null;
        endDateTime = null;
        bookedById = null;
        roomId = null;
        this.id = id;
    }
}
