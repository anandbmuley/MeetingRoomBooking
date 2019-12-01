import { Component, OnInit, Input } from '@angular/core';
import { BookingDto } from './services/booking.service';

@Component({
  selector: 'booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit {

  @Input() currentBooking: BookingDto;

  constructor() { }

  ngOnInit() {
  }

}
