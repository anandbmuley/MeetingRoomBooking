package com.merobo.builders

import com.merobo.dtos.BookingTo
import com.merobo.utils.BookingStatus

import java.text.ParseException

class BookingToBuilder {

    String id
    String teamName
    String startTime
    Date startDateTime
    String endTime
    Date endDateTime
    String bookedBy
    String bookedWhen
    String roomName
    BookingStatus status

    public BookingToBuilder() {
        id = "BID101"
        teamName = "TeamA"
        startTime = "10:00 AM"
        startDateTime = new Date()
        endTime = "11:00 AM"
        bookedBy = "Akira Khan"
        bookedWhen = "09:00 AM"
        roomName = "PINNACLE"
        status = BookingStatus.BOOKED
    }

    BookingToBuilder setId(String id) {
        this.id = id
        this
    }

    BookingToBuilder setTeamName(String teamName) {
        this.teamName = teamName
        this
    }

    BookingToBuilder setStartTime(String startTime) {
        this.startTime = startTime
        this
    }

    BookingToBuilder setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime
        this
    }

    BookingToBuilder setEndTime(String endTime) {
        this.endTime = endTime
        this
    }

    BookingToBuilder setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime
        this
    }

    BookingToBuilder setBookedBy(String bookedBy) {
        this.bookedBy = bookedBy
        this
    }

    BookingToBuilder setBookedWhen(String bookedWhen) {
        this.bookedWhen = bookedWhen
        this
    }

    BookingToBuilder setRoomName(String roomName) {
        this.roomName = roomName
        this
    }

    BookingToBuilder setStatus(BookingStatus status) {
        this.status = status
        this
    }

    public BookingTo build() throws ParseException {
        BookingTo bookingTo = new BookingTo(
                bookedBy: bookedBy,
                bookedWhen: bookedWhen,
                endTime: endTime,
                id: id,
                roomName: roomName,
                startTime: startTime,
                status: status,
                teamName: teamName
        )
        bookingTo
    }
}
