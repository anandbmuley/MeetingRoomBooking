import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


export interface ContactDto {
  emailId: string
  mobile: string
}

export interface BookedByDto {
  name: string
  contact: ContactDto
}

export interface BookingDto {
  startTime: string
  endTime: string
  bookedById: string
}

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  constructor(
    private httpClient: HttpClient
  ) { }

  getCurrent(roomId: string) {
    let url = `http://localhost:8080/merobo/api/rooms/${roomId}/bookings/today/current`
    return this.httpClient.get(url);
  }

  book(roomId: string, bookingDto: BookingDto) {
    let url = `http://localhost:8080/merobo/api/rooms/${roomId}/bookings`
    return this.httpClient.post(url, bookingDto);
  }

}
