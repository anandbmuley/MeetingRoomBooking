import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from 'src/app/auth/auth.service';

export interface ContactDto {
  emailId: string
  mobile: string
}

export interface BookedByDto {
  name: string
  contact: ContactDto
}

export interface BookingDto {
  id: string
  startTime: string
  endTime: string
  bookedById: string
}

export class Time {
  hh: number
  mm: number
  meridian: string = 'AM'

  getTime(): string {
    return this.prefixZero(this.hh) + ":" + this.prefixZero(this.mm) + " " + this.meridian;
  }

  prefixZero(value: number) {
    return value < 10 ? "0" + value : value;
  }

}

export class BookingVO {
  startTimeModel: Time;
  endTimeModel: Time;
  bookingDate: string;

  constructor() {
    this.startTimeModel = new Time();
    this.endTimeModel = new Time();
    this.bookingDate = null;
  }

}

@Injectable({
  providedIn: 'root'
})
export class BookingService {

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

  getCurrent(roomId: string) {
    let url = `http://localhost:8080/merobo/api/rooms/${roomId}/bookings/today/current`
    return this.httpClient.get(url, this.generateCommonHeaders());
  }

  getTodays(roomId: string) {
    let url = `http://localhost:8080/merobo/api/rooms/${roomId}/bookings/today`
    return this.httpClient.get(url, this.generateCommonHeaders());
  }

  cancel(roomId: string, bookingId: string) {
    let url = `http://localhost:8080/merobo/api/rooms/${roomId}/bookings/${bookingId}/cancel`
    return this.httpClient.put(url, null, this.generateCommonHeaders());
  }

  getAll(roomId: string) {
    let url = `http://localhost:8080/merobo/api/rooms/${roomId}/bookings`
    return this.httpClient.get(url, this.generateCommonHeaders());
  }

  book(roomId: string, bookingDto: BookingDto) {
    let url = `http://localhost:8080/merobo/api/rooms/${roomId}/bookings`
    return this.httpClient.post(url, bookingDto, this.generateCommonHeaders());
  }

}
