import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegistrationComponent } from './registration/registration.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { AdminComponent } from './admin/admin/admin.component';
import { AuthGuard } from './auth/auth.guard';
import { ManageRoomsComponent } from './admin/manage-rooms/manage-rooms.component';

const routes: Routes = [
  {
    path: 'login', component: LoginComponent
  },
  {
    path: 'register', component: RegistrationComponent
  },
  {
    path: 'home', component: HomeComponent
  },
  {
    path: 'admin/rooms', component : ManageRoomsComponent, canActivate: [AuthGuard]
  },
  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [AuthGuard]
    // children: [
    //   {
    //     path: '',
    //     canActivateChild : [AuthGuard],
    //     children: [
    //       { path: 'rooms', component: ManageRoomsComponent }
    //     ]
    //   }
    // ]
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
