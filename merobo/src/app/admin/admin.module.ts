import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { AdminComponent } from './admin/admin.component';
import { ManageRoomsComponent } from './manage-rooms/manage-rooms.component';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

@NgModule({
  declarations: [AdminDashboardComponent, AdminComponent, ManageRoomsComponent],
  imports: [
    BrowserModule,
    FormsModule,
    CommonModule,
    AdminRoutingModule
  ]
})
export class AdminModule { }
