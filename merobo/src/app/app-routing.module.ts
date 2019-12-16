import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AdminComponent } from './admin/admin/admin.component';
import { AuthGuard } from './auth/auth.guard';
import { ManageRoomsComponent } from './admin/manage-rooms/manage-rooms.component';
import { RoomDetailsComponent } from './room-details/room-details.component';
import { BookComponent } from './room/book/book.component';
import { AdminGuard } from './admin.guard';
import { RegistrationComponent, LogoutComponent, LoginComponent } from 'abm-authenticator';

const routes: Routes = [
  {
    path: 'login', component: LoginComponent
  },
  {
    path: 'logout', component: LogoutComponent
  },
  {
    path: 'register', component: RegistrationComponent
  },
  {
    path: 'home', component: HomeComponent, canActivate: [AuthGuard]
  },
  {
    path: 'room-details/:id', component: RoomDetailsComponent, canActivate: [AuthGuard]
  },
  {
    path: 'rooms/:id', component: BookComponent, canActivate: [AuthGuard]
  },
  {
    path: 'rooms', component: BookComponent, canActivate: [AuthGuard]
  },
  {
    path: 'admin/rooms', component: ManageRoomsComponent, canActivate: [AuthGuard, AdminGuard]
  },
  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [AuthGuard, AdminGuard]
  },
  {
    path: '', redirectTo: '/login', pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
