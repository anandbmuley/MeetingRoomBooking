import { Component, OnInit } from '@angular/core';
import { RoomService, RoomDto } from '../services/room.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

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
