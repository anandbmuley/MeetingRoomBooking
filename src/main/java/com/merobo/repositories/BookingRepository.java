package com.merobo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.merobo.beans.BookingBean;

public interface BookingRepository extends MongoRepository<BookingBean, String> {

	List<BookingBean> findByRoomName(String roomName);

}
