import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BookingService, BookingDto } from 'src/app/booking/services/booking.service';
import { RoomDto, RoomService } from 'src/app/services/room.service';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

  roomId: string;
  booking: BookingDto;
  bookingDate: string;
  startTime: string;
  endTime: string;
  rooms: RoomDto[];

  constructor(
    private route: ActivatedRoute,
    private bookingService: BookingService,
    private roomService: RoomService
  ) { }

  ngOnInit() {
    let roomId = this.route.snapshot.paramMap.get('id');
    if (roomId == undefined) {
      this.roomService.fetchAll().subscribe((rooms: RoomDto[]) => {
        this.rooms = rooms;
      });
    } else {
      this.roomId = this.route.snapshot.paramMap.get('id');
    }
  }

  onSelect(event, inputName) {
    switch (inputName) {
      case 'BookingDate':
        this.bookingDate = event.target.value;
        break;
      case 'StartTime':
        this.startTime = event.target.value;
        break;
      case 'EndTime':
        this.endTime = event.target.value;
        break;
    }
  }

  bookRoom() {
    this.booking = {
      bookedById: '5dd80b3e6115cb36f3a16df4',
      startTime: this.bookingDate + ' ' + this.startTime,
      endTime: this.bookingDate + ' ' + this.endTime
    };
    this.bookingService.book(this.roomId, this.booking).subscribe((status) => {
      console.log('Booking Saved');
    });
  }

}
