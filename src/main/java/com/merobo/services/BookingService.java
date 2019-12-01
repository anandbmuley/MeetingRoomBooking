package com.merobo.services;

import com.merobo.beans.BookingBean;
import com.merobo.dtos.BookingTo;
import com.merobo.exceptions.BookingServiceException;
import com.merobo.repositories.BookingRepository;
import com.merobo.utils.BookingStatus;
import com.merobo.utils.DateConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingValidationService bookingValidationService;

    @Autowired
    public BookingService(BookingRepository bookingRepository,
                          BookingValidationService bookingValidationService) {
        this.bookingRepository = bookingRepository;
        this.bookingValidationService = bookingValidationService;
    }

    public void cancelBooking(String bookingId) {
        Optional<BookingBean> bookingBean = bookingRepository.findById(bookingId);
        bookingBean.ifPresent($ -> {
            $.cancel();
            bookingRepository.save($);
        });
    }

    public BookingTo bookRoom(BookingTo bookingTo) throws BookingServiceException {
        BookingBean bookingBean = new BookingBean(bookingTo.getStartDateTime(), bookingTo.getEndDateTime(), bookingTo.getRoomId(), bookingTo.getBookedById());
        bookingTo.roomBooked(bookingRepository.save(bookingBean).getId());
        return bookingTo;
    }

    public List<BookingTo> getAll(String roomId, LocalDate bookedOn) {
        return getAll(roomId).stream().filter(bookingTo ->
                bookingTo.getStartDateTime().toLocalDate().equals(bookedOn)
        ).collect(Collectors.toList());
    }

    public List<BookingTo> getAll(String roomId, String bookingDate) {
        LocalDate bookedOn = DateConverterUtil.parseDate(bookingDate);
        return getAll(roomId, bookedOn);
    }

    public List<BookingTo> getAll(String roomId) {
        return bookingRepository.findByRoomIdAndStatus(roomId, BookingStatus.BOOKED)
                .stream().map(bookingBean ->
                        new BookingTo(bookingBean.getId(), bookingBean.getStartTime(), bookingBean.getEndTime(),
                                bookingBean.getBookedById(), bookingBean.getRoomId(), bookingBean.getStatus())
                ).collect(Collectors.toList());
//        List<BookingBean> beans = bookingRepository.findByEndTimeAfter(new Date());
//        List<BookingTo> bookingTos = DtoCreatorUtil.createBookingTos(beans);
//
//        for (BookingTo bookingTo : bookingTos) {
//            if (BookingStatus.CANCELLED.equals(bookingTo.getStatus())) {
//                continue;
//            }
//            if (MeetingRooms.PINNACLE.name().equals(bookingTo.getRoomName())) {
//                meetingRoomTo.getPinnacle().add(bookingTo);
//            } else if (MeetingRooms.OTHER.name().equals(bookingTo.getRoomName())) {
//                meetingRoomTo.getOther().add(bookingTo);
//            }
//        }
//        // Sorting by start time
//        Collections.sort(meetingRoomTo.getOther());
//        Collections.sort(meetingRoomTo.getPinnacle());
    }

    public Optional<BookingTo> getCurrent(String roomId) {
        return bookingRepository.findByRoomIdAndStatus(roomId, BookingStatus.BOOKED)
                .stream().filter(BookingBean::isBookedNow).findAny().map(bookingBean ->
                        new BookingTo(bookingBean.getId(), bookingBean.getStartTime(), bookingBean.getEndTime(),
                                bookingBean.getBookedById(), bookingBean.getRoomId(), bookingBean.getStatus())
                );
    }
}
