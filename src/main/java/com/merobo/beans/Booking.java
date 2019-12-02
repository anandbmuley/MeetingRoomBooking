package com.merobo.beans;

import com.merobo.utils.BookingStatus;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "bookings")
public class Booking {

    @Id
    private String id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String roomId;
    private BookingStatus status;
    private String bookedById;

    public Booking() {
    }

    public Booking(LocalDateTime startTime, LocalDateTime endTime, String roomId, String bookedById) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomId = roomId;
        this.bookedById = bookedById;
        this.status = BookingStatus.BOOKED;
    }

    public boolean isBookedNow() {
        final LocalDateTime now = LocalDateTime.now();
        return Boolean.logicalAnd(
                now.isAfter(startTime),
                now.isBefore(endTime));
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getRoomId() {
        return roomId;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public String getBookedById() {
        return bookedById;
    }

    public String toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("startTime", startTime);
        jsonObject.put("endTime", endTime);
        jsonObject.put("status", status);
        return jsonObject.toString();
    }

    public void cancel() {
        status = BookingStatus.CANCELLED;
    }

}
