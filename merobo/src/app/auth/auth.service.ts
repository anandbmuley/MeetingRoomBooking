import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { delay, tap } from 'rxjs/operators';
import { CookieService } from 'ngx-cookie-service';
import { HttpClient } from '@angular/common/http';
import { LoginDto, UserService } from '../user.service';
import { Router } from '@angular/router';

export interface AuthResponseDto {
  userId: string
  loginTime: string
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isLoggedIn = false;
  redirectUrl: string;

  constructor(private cookieService: CookieService,
    private userService: UserService,
    private router: Router) { }

  handleRedirect() {
    if (this.redirectUrl == undefined) {
      this.router.navigate(['home']);
    } else {
      this.router.navigate([this.redirectUrl]);
    }
  }

  createAuthCookie(user: AuthResponseDto) {
    this.cookieService.set('auth', 'true');
    this.cookieService.set('authId', user.userId);
  }

  getAuthId() {
    return this.cookieService.get('authId');
  }

  login(loginDto: LoginDto) {
    return this.userService.authenticate(loginDto).subscribe((foundUser: AuthResponseDto) => {
      this.createAuthCookie(foundUser);
      this.handleRedirect();
    });
  }

  logout() {
    this.isLoggedIn = false;
    this.cookieService.delete('auth');
    this.cookieService.delete('authId');
  }

  isUserLoggedIn() {
    return this.cookieService.get('auth') === 'true';
  }

  checkLogin(url: string): boolean {
    if (this.cookieService.get('auth') === 'true') {
      return true;
    }
    this.redirectUrl = url;
    this.router.navigate(['/login']);
    return false;
  }

}
