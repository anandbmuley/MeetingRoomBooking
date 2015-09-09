package com.merobo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.merobo.beans.BookingBean;

public interface BookingRepository extends MongoRepository<BookingBean, String> {

}
