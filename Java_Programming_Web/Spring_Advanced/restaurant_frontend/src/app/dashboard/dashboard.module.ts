import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard.component'; 
import { DashboardRoutes } from './dashboard.routing';


import { FlexLayoutModule } from '@angular/flex-layout';
import { MaterialModule } from '../shared/material-module';



@NgModule({
  declarations: [DashboardComponent],
  imports: [
    CommonModule,
    MaterialModule,
    FlexLayoutModule,
    RouterModule.forChild(DashboardRoutes)
  ]
})
export class DashboardModule { }
