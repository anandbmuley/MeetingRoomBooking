import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

export class RegistrationDto {
  name: String
  username: String
  password: String
  emailId: String
  contactNo: String
}

export class LoginDto {
  username: String
  password: String
}

export class UserDto {
  name: string
}

@Injectable({
  providedIn: 'root'
})
export class UserService {

  baseUrl = "http://localhost:8080/merobo/api/auth";
  authBaseUrl = "http://localhost:8080/merobo/api/auth/login";

  constructor(private httpClient: HttpClient) { }

  register(user: RegistrationDto) {
    return this.httpClient.post(this.baseUrl, user);
  }

  authenticate(login: LoginDto) {
    return this.httpClient.post(this.authBaseUrl, login)
      .pipe(
        retry(3),
        catchError(this.handleError)
      );
  }

  getUser(userId: string) {
    return this.httpClient.get(this.baseUrl + "/" + userId);
  }

  handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      console.error('Client side error occured');
    } else if (error.status == 403) {
      return throwError('Authentication Failed');
    }
    console.error(`Backend returned and error ${error.status}, body was ${error.error}`);
    return throwError('Something went wrong');
  }

}
