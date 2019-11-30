import { Component, OnInit } from '@angular/core';
import { RoomService, RoomDto } from 'src/app/services/room.service';

@Component({
  selector: 'app-manage-rooms',
  templateUrl: './manage-rooms.component.html',
  styleUrls: ['./manage-rooms.component.css']
})
export class ManageRoomsComponent implements OnInit {

  room: RoomDto;
  rooms: RoomDto[];

  constructor(private roomService: RoomService) { }

  newRoom() {
    this.room = {
      id: '', name: ''
    };
  }

  addRoom() {
    this.roomService.add(this.room).subscribe(() => {
      this.newRoom();
      this.fetchRooms();
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
