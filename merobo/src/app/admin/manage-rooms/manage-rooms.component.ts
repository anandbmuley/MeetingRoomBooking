import { Component, OnInit, Input, ViewChild, ElementRef } from '@angular/core';
import { RoomService, RoomDto } from 'src/app/services/room.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSelectChange } from '@angular/material/select';

@Component({
  selector: 'app-manage-rooms',
  templateUrl: './manage-rooms.component.html',
  styleUrls: ['./manage-rooms.component.css']
})
export class ManageRoomsComponent implements OnInit {

  room: RoomDto;
  rooms: RoomDto[];
  message = '';
  editMode: boolean = false;
  @ViewChild('roomImageSelector') roomImageSelector: ElementRef;

  constructor(private roomService: RoomService,
    private snackBar: MatSnackBar) { }

  newRoom() {
    this.room = {
      id: null,
      name: null,
      hasProjector: false,
      hasAc: false,
      capacity: 0,
      imageUrl: null
    };
  }

  addRoom() {
    this.roomService.add(this.room).subscribe(() => {
      this.newRoom();
      this.fetchRooms();
      this.message = 'Room addedd successfully';
    });
  }

  openFileSelectionDialog(event) {
    this.roomImageSelector.nativeElement.click();
  }

  saveRoom() {
    this.roomService.update(this.room).subscribe(() => {
      this.newRoom();
      this.fetchRooms();
      this.message = 'Room updated successfully';
    });
  }

  changedOption(selectionChanged: Event) {
    this.message = '';
    this.editMode = true;
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
