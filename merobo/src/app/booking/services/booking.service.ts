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

export class Time {
  hh: number
  mm: number
  meridian: string

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

  constructor(
    private httpClient: HttpClient
  ) { }

  getCurrent(roomId: string) {
    let url = `http://localhost:8080/merobo/api/rooms/${roomId}/bookings/today/current`
    return this.httpClient.get(url);
  }

  getTodays(roomId: string) {
    let url = `http://localhost:8080/merobo/api/rooms/${roomId}/bookings/today`
    return this.httpClient.get(url);
  }

  getAll(roomId: string) {
    let url = `http://localhost:8080/merobo/api/rooms/${roomId}/bookings`
    return this.httpClient.get(url);
  }

  book(roomId: string, bookingDto: BookingDto) {
    let url = `http://localhost:8080/merobo/api/rooms/${roomId}/bookings`
    return this.httpClient.post(url, bookingDto);
  }

}
