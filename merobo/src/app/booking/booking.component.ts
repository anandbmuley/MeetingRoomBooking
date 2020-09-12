import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { BookingDto, BookingService } from './services/booking.service';
import { UserDto, UserService } from '../user.service';
import { RoomDto } from '../services/room.service';

@Component({
  selector: 'booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit {

  @Input() data: BookingDto;
  @Input() roomId: string;
  user: UserDto;
  room: RoomDto;
  @Input() displayPhone: boolean = false;

  @Output() bookingCancelled = new EventEmitter();

  constructor(private userService: UserService,
    private bookingService: BookingService) { }

  ngOnInit() {
    this.userService.getUser(this.data.bookedById).subscribe((userDto: UserDto) => {
      this.user = userDto;
    });

  }

  showPhoneNumber() {
    this.displayPhone = true;
  }

  cancel() {
    this.bookingService.cancel(this.roomId, this.data.id).subscribe(() => {

    });
  }

}
