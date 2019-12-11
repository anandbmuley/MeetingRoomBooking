import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { LoginDto } from '../user.service';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  login: LoginDto;

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.login = {
      username: null,
      password: null
    }
  }

  authenticate() {
    this.authService.login(this.login);
  }

}
