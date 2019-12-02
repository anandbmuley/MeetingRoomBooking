package com.merobo.repositories;

import com.merobo.beans.Booking;
import com.merobo.utils.BookingStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, String> {

    List<Booking> findByEndTimeAfter(Date startTime);

    List<Booking> findByRoomIdAndStatus(String roomId, BookingStatus bookingStatus);

}
