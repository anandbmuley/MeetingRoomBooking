import { Component, OnInit, Input } from '@angular/core';
import { BookingDto } from './services/booking.service';
import { UserDto, UserService } from '../user.service';

@Component({
  selector: 'booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit {

  @Input() data: BookingDto;
  user: UserDto;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.getUser(this.data.bookedById).subscribe((userDto: UserDto) => {
      this.user = userDto;
    });

  }

}
