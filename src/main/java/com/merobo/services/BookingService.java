package com.merobo.services;

import com.merobo.beans.Booking;
import com.merobo.dtos.BookingDto;
import com.merobo.exceptions.BookingServiceException;
import com.merobo.exceptions.UnAuthorizedAccessException;
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

    public void cancelBooking(String authId, String roomId, String bookingId) throws UnAuthorizedAccessException {
        Optional<Booking> bookingBean = bookingRepository.findById(bookingId);
        Booking bookedRoom = bookingBean.filter(booking -> booking.getBookedById().equals(authId))
                .filter(booking -> booking.getRoomId().equals(roomId)).orElseThrow(UnAuthorizedAccessException::new);
        bookedRoom.cancel();
        bookingRepository.save(bookedRoom);
    }

    public BookingDto bookRoom(BookingDto bookingDto) throws BookingServiceException {
        Booking booking = new Booking(bookingDto.getStartDateTime(), bookingDto.getEndDateTime(), bookingDto.getRoomId(), bookingDto.getBookedById());
        bookingDto.roomBooked(bookingRepository.save(booking).getId());
        return bookingDto;
    }

    public List<BookingDto> getAll(String roomId, String authId, LocalDate bookedOn) {
        return getAll(roomId, authId).stream().filter(bookingTo ->
                bookingTo.getStartDateTime().toLocalDate().equals(bookedOn)
        ).collect(Collectors.toList());
    }

    public List<BookingDto> getAll(String roomId, String authId, String bookingDate) {
        LocalDate bookedOn = DateConverterUtil.parseDate(bookingDate);
        return getAll(roomId, authId, bookedOn);
    }

    public List<BookingDto> getAll(String roomId, String authId) {
        return bookingRepository.findByRoomIdAndStatus(roomId, BookingStatus.BOOKED)
                .stream().map(bookingBean -> {
                    BookingDto bookingDto = new BookingDto(bookingBean.getId(), bookingBean.getStartTime(), bookingBean.getEndTime(),
                            bookingBean.getBookedById(), bookingBean.getRoomId(), bookingBean.getStatus());
                    bookingDto.setBookedByMe(bookingBean.getBookedById().equals(authId));
                    return bookingDto;
                }).collect(Collectors.toList());
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

    public Optional<BookingDto> getCurrent(String roomId) {
        return bookingRepository.findByRoomIdAndStatus(roomId, BookingStatus.BOOKED)
                .stream().filter(Booking::isBookedNow).findAny().map(bookingBean ->
                        new BookingDto(bookingBean.getId(), bookingBean.getStartTime(), bookingBean.getEndTime(),
                                bookingBean.getBookedById(), bookingBean.getRoomId(), bookingBean.getStatus())
                );
    }
}
