import { Component, OnInit } from '@angular/core';
import { RoomDto, RoomService } from '../services/room.service';
import { Router } from '@angular/router';

@Component({
  selector: 'booking-summary',
  templateUrl: './booking-summary.component.html',
  styleUrls: ['./booking-summary.component.css']
})
export class BookingSummaryComponent implements OnInit {

  rooms: RoomDto[];

  constructor(private roomService: RoomService,
    private router: Router) { }

  ngOnInit() {
    this.roomService.fetchAll().subscribe((rooms: RoomDto[]) => {
      this.rooms = rooms;
    });
  }

  viewDetails(room: RoomDto) {
    this.router.navigate(['room-details', room.id]);
  }

}
