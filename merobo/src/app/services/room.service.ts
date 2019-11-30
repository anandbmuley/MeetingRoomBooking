import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  url = "http://localhost:8080/merobo/api/admin/rooms";

  constructor(private httpClient: HttpClient) { }

  fetchAll() {
    return this.httpClient.get(this.url);
  }

  add(room: RoomDto){
    return this.httpClient.post(this.url, room);
  }


}

export interface RoomDto {
  id: string
  name: string
}