package com.merobo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.merobo.beans.BookingBean;
import com.merobo.dtos.BookingTo;
import com.merobo.repositories.BookingRepositories;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepositories bookingRepositories;

	List<BookingBean> list = new ArrayList<BookingBean>();

	public BookingTo bookRoom(BookingTo bookingTo) {
         System.out.println("hello in implementation");
		System.out.println(bookingTo);
		
		BookingBean bookingBean = new BookingBean();
		bookingBean.setTeam(bookingTo.getTeam());
		bookingBean.setDate(bookingTo.getDate());
		bookingBean.setStartTime(bookingTo.getStartTime());
		bookingBean.setEndTime(bookingTo.getEndTime());
		
		System.out.println(bookingBean);

		bookingBean = bookingRepositories.save(bookingBean);

		BookingTo bookingTo2 = new BookingTo();
		bookingTo2.setTeam(bookingBean.getTeam());
		bookingTo2.setDate(bookingBean.getDate());
		bookingTo2.setStartTime(bookingBean.getStartTime());
		bookingTo2.setEndTime(bookingBean.getEndTime());
		return bookingTo2;

	}

	public List<BookingBean> deleteTeam(String team) {
		list = bookingRepositories.deleteByTeam(team);
		return list;

	}

	public List<BookingBean> findAll() {
		// TODO Auto-generated method stub
		List<BookingBean> list = new ArrayList<BookingBean>();
		list = bookingRepositories.findAll();
		return list;
	}

	public BookingTo findTeamBooking(String name) {
		System.out.println("inside impl");
		BookingBean bookingBean = bookingRepositories.findByTeam(name);
		System.out.println(bookingBean);
		if(bookingBean!=null){
		BookingTo bookingTo = new BookingTo();
		bookingTo.setTeam(bookingBean.getTeam());
		bookingTo.setDate(bookingBean.getDate());
		bookingTo.setStartTime(bookingBean.getStartTime());
		bookingTo.setEndTime(bookingBean.getEndTime());
		return bookingTo;
		}
		else{
			return null; 
		}

	}
}
