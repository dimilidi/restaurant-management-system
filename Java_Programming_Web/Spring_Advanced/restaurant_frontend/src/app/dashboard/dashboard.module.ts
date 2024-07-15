import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard.component';
import { DashboardRoutes } from './dashboard.routing';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MaterialModule } from '../shared/material-module';
import { BestSellerChartComponent } from '../best-seller-chart/best-seller-chart.component';
import { TopEmployeeChartComponent } from '../top-employee-chart/top-employee-chart.component';
import { RegularGuestsComponent } from '../regular-guests/regular-guests.component';


@NgModule({
  declarations: [
    DashboardComponent,
    BestSellerChartComponent,
    TopEmployeeChartComponent,
    RegularGuestsComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    FlexLayoutModule,
    RouterModule.forChild(DashboardRoutes),
  ],
})
export class DashboardModule {}
