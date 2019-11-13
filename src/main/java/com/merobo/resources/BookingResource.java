package com.merobo.resources;

import com.merobo.dtos.BookingTo;
import com.merobo.exceptions.BookingServiceException;
import com.merobo.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bookings")
public class BookingResource {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity bookRoom(BookingTo bookingTo) {
        ResponseEntity response = null;
        try {
            bookingService.bookRoom(bookingTo);
            response = ResponseEntity.ok(bookingTo.getId());
        } catch (BookingServiceException e) {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getCause().getMessage());
        }
        return response;
    }

    @GetMapping
    public ResponseEntity getAllBookings() {
        return ResponseEntity.ok(bookingService.getAll());
    }

    @DeleteMapping
    public ResponseEntity cancelBooking(@RequestParam("bid") String bookingId) {
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok().build();
    }

}
