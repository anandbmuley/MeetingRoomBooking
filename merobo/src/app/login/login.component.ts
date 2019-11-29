import { Component, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { UserService, LoginDto } from '../user.service';
import { Router } from '@angular/router';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  login: LoginDto;
  message = '';

  constructor(private userService: UserService,
    private router: Router,
    private authService: AuthService) {
  }

  ngOnInit() {
    this.login = {
      username: "hulkhogan",
      password: "password@123"
    }

  }

  

  authenticate() {
    this.authService.login(this.login);
    // this.userService.authenticate(this.login).subscribe((response)=>{
    //   this.message = 'Authentication Successful !';
    //   this.authService.isLoggedIn = true;
    //   console.log("REDIRECTING : "+this.authService.redirectUrl);
    //   if( this.authService.redirectUrl == undefined ){
    //     this.router.navigate(['home']);
    //   } else {
    //     this.router.navigate([this.authService.redirectUrl]);
    //   }
    // },(error)=>{
    //   this.message = 'Something Went Wrong !';
    // });
  }

}
