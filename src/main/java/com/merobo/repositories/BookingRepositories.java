package com.merobo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.merobo.beans.BookingBean;

public interface BookingRepositories extends
		MongoRepository<BookingBean, String> {

	List<BookingBean> deleteByTeam(String team);

	BookingBean findByTeam(String name);

}
