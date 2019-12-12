package com.merobo.resources;

import com.merobo.dtos.BookingDto;
import com.merobo.exceptions.BookingServiceException;
import com.merobo.exceptions.UnAuthorizedAccessException;
import com.merobo.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    @PutMapping("{bookingId}/cancel")
    public ResponseEntity cancel(@PathVariable("id") String roomId, @PathVariable("bookingId") String bookingId, @RequestHeader("auth-id") String authId) throws UnAuthorizedAccessException {
        bookingService.cancelBooking(authId, roomId, bookingId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity bookRoom(@PathVariable("id") String roomId, @RequestBody BookingDto bookingDto) {
        ResponseEntity response = null;
        try {
            bookingDto.setRoomId(roomId);
            bookingService.bookRoom(bookingDto);
            response = ResponseEntity.created(URI.create(bookingDto.getId())).build();
        } catch (BookingServiceException e) {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getCause().getMessage());
        }
        return response;
    }

    @GetMapping
    public ResponseEntity getAllBookings(@PathVariable("id") String roomId, @RequestHeader("auth-id") String authId) {
        return ResponseEntity.ok(bookingService.getAll(roomId, authId));
    }

    @GetMapping("today")
    public ResponseEntity<List<BookingDto>> getTodaysBookings(@PathVariable("id") String roomId, @RequestHeader("auth-id") String authId) {
        List<BookingDto> todaysBookings = bookingService.getAll(roomId, authId, LocalDate.now());
        return CollectionUtils.isEmpty(todaysBookings) ? ResponseEntity.notFound().build() : ResponseEntity.ok(todaysBookings);
    }

    @GetMapping("{bookingDate}")
    public ResponseEntity<List<BookingDto>> getBookingsFor(@PathVariable("id") String roomId, @PathVariable String bookingDate) {
        return ResponseEntity.ok(bookingService.getAll(roomId, bookingDate));
    }

}
