import { Component } from '@angular/core';
import { AuthService } from './auth/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'MEROBO';
  authenticated = false;

  constructor(private authService: AuthService) {}

  isUserLoggedIn(): boolean {
    return this.authService.isUserLoggedIn();
  }

}
