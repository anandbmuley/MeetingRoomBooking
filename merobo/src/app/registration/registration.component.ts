import { Component, OnInit } from '@angular/core';
import { UserService, RegistrationDto } from '../user.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {

  user:RegistrationDto = new RegistrationDto();
  message = '';

  constructor(private userService:UserService) { }

  register(){
    this.userService.register(this.user).subscribe((response)=>{
      this.message = 'Registration Successful !';
    });
  }

}
