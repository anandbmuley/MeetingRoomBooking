import { Component } from '@angular/core';
import { UserService, RegistrationDto } from '../user.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {

  user: RegistrationDto = new RegistrationDto();

  constructor(private userService: UserService,
    private snackBar: MatSnackBar) { }

  register() {
    this.userService.register(this.user).subscribe(() => {
      this.snackBar.open('Registration Successful 🍕', null, {
        duration: 3000
      });
      this.user = new RegistrationDto();
    });
  }

}