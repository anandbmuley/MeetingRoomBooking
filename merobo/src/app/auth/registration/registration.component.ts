import { Component, OnInit } from '@angular/core';
import { RegistrationDto, UserService } from 'src/app/user.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  registration = new RegistrationDto();
  message = '';

  constructor(private userService: UserService) { }

  ngOnInit() {
  }

  save() {
    this.userService.register(this.registration).subscribe((success) => {
      this.registration = new RegistrationDto();
      this.message = 'Registration Successful !';
    }, (error: HttpErrorResponse) => {
      this.message = error.message;
    });
  }

}
