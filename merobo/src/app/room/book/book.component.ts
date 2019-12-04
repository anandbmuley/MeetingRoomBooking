import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BookingService, BookingDto } from 'src/app/booking/services/booking.service';
import { RoomDto, RoomService } from 'src/app/services/room.service';
import { AuthService } from 'src/app/auth/auth.service';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

  roomId: string = "-1";
  booking: BookingDto;
  bookingDate: string;
  startTime: string;
  endTime: string;
  rooms: RoomDto[];

  message = '';

  constructor(
    private route: ActivatedRoute,
    private bookingService: BookingService,
    private roomService: RoomService,
    private authService: AuthService
  ) { }

  initializeMaterialComponents() {
    var datepickerElem = document.querySelectorAll('.datepicker');
    var instances = M.Datepicker.init(datepickerElem, { format: 'dd-mm-yyyy' });

    var timepickerEle = document.querySelectorAll('.timepicker');
    var instances = M.Timepicker.init(timepickerEle, {});

    var elems = document.querySelectorAll('select');
    var instances = M.FormSelect.init(elems, {});
  }

  ngOnInit() {
    this.initializeMaterialComponents();
    let roomId = this.route.snapshot.paramMap.get('id');
    if (roomId == undefined) {
      this.roomService.fetchAll().subscribe((rooms: RoomDto[]) => {
        this.rooms = rooms;
      });
    } else {
      this.roomId = roomId;
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
      bookedById: this.authService.getAuthId(),
      startTime: this.bookingDate + ' ' + this.startTime,
      endTime: this.bookingDate + ' ' + this.endTime
    };
    this.bookingService.book(this.roomId, this.booking).subscribe((status) => {
      this.message = 'Booking saved successfully !';
    });
  }

}
