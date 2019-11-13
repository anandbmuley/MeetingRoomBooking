import { Component, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { UserService, LoginDto } from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  login:LoginDto = new LoginDto();
  message = '';

  constructor(private userService:UserService) { }

  authenticate(){
      this.userService.authenticate(this.login).subscribe((response)=>{
        this.message = 'Authentication Successful !';
      },(error)=>{
        this.message = 'Something Went Wrong !';
      });
  }

}
