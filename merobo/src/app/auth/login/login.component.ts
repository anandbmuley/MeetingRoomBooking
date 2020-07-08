import { Component, OnInit } from '@angular/core';
import { Validators, FormControl } from '@angular/forms';
import { LoginDto, UserService } from 'src/app/user.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  login = new LoginDto();

  constructor(private authService: AuthService) { }

  ngOnInit() {
  }

  authenticate() {
    this.authService.login(this.login);
  }

}
