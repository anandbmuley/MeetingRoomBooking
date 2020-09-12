import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  getAllRoomsApiUrl = "http://localhost:8080/merobo/api/rooms"
  url = "http://localhost:8080/merobo/api/admin/rooms";

  constructor(private httpClient: HttpClient,
    private authService: AuthService) { }

  generateCommonHeaders(): Object {
    let httpOptions = {
      headers: new HttpHeaders({
        'user_id': this.authService.getAuthId(),
        'auth_token': this.authService.getAuthToken()
      })
    }
    return httpOptions;
  }


  fetchAll() {
    return this.httpClient.get(this.getAllRoomsApiUrl, this.generateCommonHeaders());
  }

  add(room: RoomDto) {
    return this.httpClient.post(this.url, room, this.generateCommonHeaders());
  }

  update(room: RoomDto) {
    return this.httpClient.put(this.url + "/" + room.id, room, this.generateCommonHeaders());
  }

  findOne(roomId: string) {
    return this.httpClient.get(this.getAllRoomsApiUrl + "/" + roomId, this.generateCommonHeaders());
  }

}

export interface RoomDto {
  id: string
  name: string
  hasProjector: boolean
  hasAc: boolean
  capacity: number
  imageUrl: string
}
