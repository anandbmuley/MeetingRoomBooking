import { Component, OnInit } from '@angular/core';
import { RoomService, RoomDto } from 'src/app/services/room.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-manage-rooms',
  templateUrl: './manage-rooms.component.html',
  styleUrls: ['./manage-rooms.component.css']
})
export class ManageRoomsComponent implements OnInit {

  room: RoomDto;
  rooms: RoomDto[];

  constructor(private roomService: RoomService,
    private snackBar: MatSnackBar) { }

  newRoom() {
    this.room = {
      id: null,
      name: null,
      hasProjector: false,
      hasAc: false,
      capacity: 0
    };
  }

  addRoom() {
    this.roomService.add(this.room).subscribe(() => {
      this.newRoom();
      this.fetchRooms();
      this.snackBar.open('Room addedd successfully', null, {
        duration: 3000
      });
    });
  }

  fetchRooms() {
    this.roomService.fetchAll().subscribe((roomsData: RoomDto[]) => {
      this.rooms = roomsData;
    });
  }

  ngOnInit() {
    this.newRoom();
    this.fetchRooms();
  }

}
