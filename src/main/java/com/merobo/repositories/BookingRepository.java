package com.merobo.repositories;

import com.merobo.beans.BookingBean;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface BookingRepository extends MongoRepository<BookingBean, String> {

	List<BookingBean> findByRoomName(String roomName);

	List<BookingBean> findByEndTimeAfter(Date startTime);

}
