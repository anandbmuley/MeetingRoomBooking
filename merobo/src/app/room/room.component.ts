import { Component, OnInit, Input } from '@angular/core';
import { RoomDto } from '../services/room.service';
import { BookingService, BookingDto } from '../booking/services/booking.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.css']
})
export class RoomComponent implements OnInit {

  @Input() showBookings = false;
  @Input() room: RoomDto;

  currentBooking: BookingDto;
  message = '';
  isAvailable = false;

  constructor(private bookingService: BookingService) { }

  ngOnInit() {
    this.getCurrentBooking();
  }

  getCurrentBooking() {
    this.bookingService.getCurrent(this.room.id).subscribe((booking: BookingDto) => {
      this.currentBooking = booking;
      this.isAvailable = false;
    }, (error: HttpErrorResponse) => {
      if (error.status == 404) {
        this.message = 'AVAILABLE';
        this.isAvailable = true;
      }
      // console.log("Something went wrong");
    });
  }

}
