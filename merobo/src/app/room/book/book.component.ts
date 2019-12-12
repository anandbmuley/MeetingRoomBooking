import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BookingService, BookingDto, BookingVO } from 'src/app/booking/services/booking.service';
import { RoomDto, RoomService } from 'src/app/services/room.service';
import { AuthService } from 'src/app/auth/auth.service';
import { DatePipe } from '@angular/common';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

  roomId: string = "-1";
  booking: BookingDto;
  rooms: RoomDto[];
  hours: Array<number>;
  minutes: Array<number>;
  startHour: number;
  roomName: string;

  bookingVO: BookingVO = new BookingVO();

  constructor(
    private route: ActivatedRoute,
    private bookingService: BookingService,
    private roomService: RoomService,
    private authService: AuthService,
    private snackBar: MatSnackBar
  ) {
    this.hours = new Array();
    for (let i = 1; i <= 12; i++) {
      this.hours.push(i);
    }

    this.minutes = new Array();
    for (let i = 0; i < 60; i += 15) {
      this.minutes.push(i);
    }
  }

  ngOnInit() {
    let roomId = this.route.snapshot.paramMap.get('id');
    if (roomId == undefined) {
      this.roomService.fetchAll().subscribe((rooms: RoomDto[]) => {
        this.rooms = rooms;
      });
    } else {
      this.roomService.findOne(roomId).subscribe((room: RoomDto) => {
        this.roomName = room.name;
      });
      this.roomId = roomId;
    }
  }

  bookRoom() {
    let datePipe = new DatePipe('en-US');
    let bookingDtFormatted = datePipe.transform(this.bookingVO.bookingDate, 'dd-MM-yyyy');
    this.booking = {
      id: null,
      bookedById: this.authService.getAuthId(),
      startTime: bookingDtFormatted + ' ' + this.bookingVO.startTimeModel.getTime(),
      endTime: bookingDtFormatted + ' ' + this.bookingVO.endTimeModel.getTime()
    };
    this.bookingService.book(this.roomId, this.booking).subscribe((status) => {
      this.snackBar.open('Room booked successfully !', null, {
        duration: 3000
      });
      this.bookingVO = new BookingVO();
    });
  }

}
