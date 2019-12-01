import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  getAllRoomsApiUrl = "http://localhost:8080/merobo/api/rooms"
  url = "http://localhost:8080/merobo/api/admin/rooms";

  constructor(private httpClient: HttpClient) { }

  fetchAll() {
    return this.httpClient.get(this.getAllRoomsApiUrl);
  }

  add(room: RoomDto) {
    return this.httpClient.post(this.url, room);
  }

  findOne(roomId: string) {
    return this.httpClient.get(this.getAllRoomsApiUrl + "/" + roomId);
  }

}

export interface RoomDto {
  id: string
  name: string
  hasProjector: boolean
  hasAc: boolean
  capacity: number
}