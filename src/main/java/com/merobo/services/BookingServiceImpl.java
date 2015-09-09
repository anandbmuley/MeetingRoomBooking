package com.merobo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.merobo.beans.BookingBean;
import com.merobo.dtos.BookingTo;
import com.merobo.repositories.BookingRepository;
import com.merobo.utils.BeanCreatorUtil;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Override
	public BookingTo bookRoom(BookingTo bookingTo) {
		BookingBean bookingBean = BeanCreatorUtil.createBookingBean(bookingTo);
		bookingRepository.save(bookingBean);
		bookingTo.setId(bookingBean.getId());
		return bookingTo;
	}

	@Override
	public List<BookingTo> getAll() {
		List<BookingBean> beans = bookingRepository.findAll();
		return BeanCreatorUtil.createBookingTos(beans);
	}
}
