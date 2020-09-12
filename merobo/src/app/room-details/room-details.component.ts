import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RoomService, RoomDto } from '../services/room.service';
import { BookingService, BookingDto } from '../booking/services/booking.service';

@Component({
  selector: 'app-room-details',
  templateUrl: './room-details.component.html',
  styleUrls: ['./room-details.component.css']
})
export class RoomDetailsComponent implements OnInit {

  room: RoomDto;
  bookings: BookingDto[];

  message: string;

  constructor(
    private route: ActivatedRoute,
    private roomService: RoomService,
    private router: Router,
    private bookingService: BookingService
  ) { }

  ngOnInit() {
    let roomId = this.route.snapshot.paramMap.get('id');
    this.roomService.findOne(roomId).subscribe((roomData: RoomDto) => {
      this.room = roomData;
    });
    this.bookingService.getTodays(roomId).subscribe((bookings: BookingDto[]) => {
      this.bookings = bookings;
    }, (errorResponse) => {
      if (errorResponse.status == 404) {
        this.message = 'No bookings for today';
      }
    });
  }

  viewBookRoomPage(roomId: string) {
    this.router.navigate(['rooms', roomId])
  }

}
