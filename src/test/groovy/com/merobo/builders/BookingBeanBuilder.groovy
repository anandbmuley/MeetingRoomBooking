package com.merobo.builders

import com.merobo.beans.BookingBean
import com.merobo.utils.BookingStatus
import com.merobo.utils.DateConverterUtil
import com.merobo.utils.MeetingRooms

import java.text.ParseException

class BookingBeanBuilder {

    String id
    String teamName
    Date startTime
    Date endTime
    String bookedBy
    String bookedWhen
    String roomName
    BookingStatus status

    public BookingBeanBuilder() throws ParseException {
        id = "562ba47c44ae605f13522a83"
        teamName = "CVoS"
        startTime = DateConverterUtil.toDate("02:00 PM",
                DateConverterUtil.PATTERN_HH_MM_MERIDIAN)
        endTime = DateConverterUtil.toDate("03:00 PM",
                DateConverterUtil.PATTERN_HH_MM_MERIDIAN)
        bookedBy = "Aron Johnson"
        bookedWhen = "08:00 AM"
        roomName = MeetingRooms.PINNACLE.name()
        status = BookingStatus.BOOKED
    }

    public BookingBean build() {
        BookingBean bookingBean = new BookingBean(
                id: id,
                teamName: teamName,
                startTime: startTime,
                endTime: endTime,
                bookedBy: bookedBy,
                bookedWhen: bookedWhen,
                roomName: roomName,
                status: status
        )
        bookingBean
    }

}
