import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegistrationComponent } from './registration/registration.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { AdminModule } from './admin/admin.module';

import { CookieService } from 'ngx-cookie-service';
import { RoomService } from './services/room.service';
import { ManageRoomsComponent } from './admin/manage-rooms/manage-rooms.component';

@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    LoginComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AdminModule,
    AppRoutingModule
  ],
  providers: [ CookieService, RoomService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
