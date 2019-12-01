import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RoomService, RoomDto } from '../services/room.service';
import { BookingService } from '../booking/services/booking.service';

@Component({
  selector: 'app-room-details',
  templateUrl: './room-details.component.html',
  styleUrls: ['./room-details.component.css']
})
export class RoomDetailsComponent implements OnInit {

  room: RoomDto;

  constructor(
    private route: ActivatedRoute,
    private roomService: RoomService,
    private bookingService: BookingService,
    private router: Router
  ) { }

  ngOnInit() {
    let roomId = this.route.snapshot.paramMap.get('id');
    this.roomService.findOne(roomId).subscribe((roomData: RoomDto) => {
      this.room = roomData;
    });
  }

  viewBookRoomPage(roomId: string) {
    this.router.navigate(['rooms',roomId])
  }

}
