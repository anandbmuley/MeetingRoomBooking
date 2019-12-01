package com.merobo.resources;

import com.merobo.dtos.BookingTo;
import com.merobo.exceptions.BookingServiceException;
import com.merobo.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("rooms/{id}/bookings")
public class BookingResource {

    private final BookingService bookingService;

    @Autowired
    public BookingResource(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("today/current")
    public ResponseEntity getCurrentBookings(@PathVariable("id") String roomId) {
        return bookingService.getCurrent(roomId).map(ResponseEntity::ok).orElseGet(ResponseEntity.notFound()::build);
    }

    @PostMapping
    public ResponseEntity bookRoom(@PathVariable("id") String roomId, @RequestBody BookingTo bookingTo) {
        ResponseEntity response = null;
        try {
            bookingTo.setRoomId(roomId);
            bookingService.bookRoom(bookingTo);
            response = ResponseEntity.ok(bookingTo.getId());
        } catch (BookingServiceException e) {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getCause().getMessage());
        }
        return response;
    }

    @GetMapping
    public ResponseEntity getAllBookings(@PathVariable("id") String roomId) {
        return ResponseEntity.ok(bookingService.getAll(roomId));
    }

    @GetMapping("today")
    public ResponseEntity<List<BookingTo>> getTodaysBookings(@PathVariable("id") String roomId) {
        return ResponseEntity.ok(bookingService.getAll(roomId, LocalDate.now()));
    }

    @GetMapping("{bookingDate}")
    public ResponseEntity<List<BookingTo>> getBookingsFor(@PathVariable("id") String roomId, @PathVariable String bookingDate) {
        return ResponseEntity.ok(bookingService.getAll(roomId, bookingDate));
    }

    @DeleteMapping
    public ResponseEntity cancelBooking(@RequestParam("bid") String bookingId) {
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok().build();
    }

}
