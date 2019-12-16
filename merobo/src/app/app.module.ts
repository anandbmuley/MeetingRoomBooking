import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { AdminModule } from './admin/admin.module';

import { CookieService } from 'ngx-cookie-service';
import { RoomService } from './services/room.service';
import { RoomComponent } from './room/room.component';
import { BookingComponent } from './booking/booking.component';
import { RoomDetailsComponent } from './room-details/room-details.component';
import { BookingSummaryComponent } from './booking-summary/booking-summary.component';
import { BookingService } from './booking/services/booking.service';
import { BookComponent } from './room/book/book.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatMenuModule } from '@angular/material/menu';
import { MatSelectModule } from '@angular/material/select';
import { MatNativeDateModule } from '@angular/material/core';
import { MatListModule } from '@angular/material/list';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { AuthenticatorModule } from 'abm-authenticator';
import { environment } from '../environments/environment';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RoomComponent,
    BookingComponent,
    RoomDetailsComponent,
    BookingSummaryComponent,
    BookComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AdminModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatDatepickerModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    MatGridListModule,
    MatMenuModule,
    MatSelectModule,
    MatNativeDateModule,
    MatListModule,
    MatSnackBarModule,
    AuthenticatorModule.forRoot(environment)
  ],
  providers: [CookieService, RoomService, BookingService],
  bootstrap: [AppComponent]
})
export class AppModule { }
