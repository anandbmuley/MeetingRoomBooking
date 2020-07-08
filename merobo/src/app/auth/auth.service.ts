import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { LoginDto, UserService } from '../user.service';
import { Router } from '@angular/router';

export interface AuthResponseDto {
  userId: string
  loginTime: string
  admin: string
  token: string
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  loggedIn = false;
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
    this.cookieService.set('admin', user.admin);
    this.cookieService.set('authToken', user.token);
  }

  getAuthToken(): string {
    return this.cookieService.get('authToken');
  }

  getAuthId() {
    return this.cookieService.get('authId');
  }

  login(loginDto: LoginDto) {
    return this.userService.authenticate(loginDto).subscribe((foundUser: AuthResponseDto) => {
      this.createAuthCookie(foundUser);
      this.handleRedirect();
      this.loggedIn = true;
    });
  }

  logout() {
    this.loggedIn = false;
    this.cookieService.deleteAll();
    this.cookieService.delete('auth');
    this.cookieService.delete('authId');
    this.cookieService.delete('admin');
    this.cookieService.delete('authToken');
    this.router.navigate(['/login']);
  }

  isUserLoggedIn(): boolean {
    return this.cookieService.get('auth') === 'true';
  }

  isAdmin(): boolean {
    return this.cookieService.get('admin') === 'true';
  }

  checkLogin(url: string): boolean {
    if (this.cookieService.get('auth') === 'true') {
      return true;
    }
    this.redirectUrl = url;
    this.router.navigate(['/login']);
    return false;
  }

  checkAdmin(url: string): boolean {
    if (this.isAdmin()) {
      return true;
    }
    this.redirectUrl = url;
    this.router.navigate(['/login']);
    return false;
  }

}
