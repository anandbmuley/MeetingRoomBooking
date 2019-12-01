package com.merobo.repositories;

import com.merobo.beans.BookingBean;
import com.merobo.utils.BookingStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface BookingRepository extends MongoRepository<BookingBean, String> {

    List<BookingBean> findByEndTimeAfter(Date startTime);

    List<BookingBean> findByRoomIdAndStatus(String roomId, BookingStatus bookingStatus);

}
