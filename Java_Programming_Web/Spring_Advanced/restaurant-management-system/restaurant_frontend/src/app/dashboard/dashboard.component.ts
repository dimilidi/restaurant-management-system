import { AfterViewInit, Component, OnInit } from '@angular/core';
import { DashboardService } from '../services/dashboard.service';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { SnackbarService } from '../services/snackbar.service';
import { GlobalConstants } from '../shared/global-constants';
import { ProductService } from '../services/product.service';
import { BillService } from '../services/bill.service';
import { ReportService } from '../services/report.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  responseMessage: any;
  data: any;
  bestSellers: any;

  constructor(
    private dashboardService: DashboardService,
    private reportService:ReportService,
    private ngxService: NgxUiLoaderService,
    private snackbarService: SnackbarService
  ) {
    ngxService.start();
    this.dashboardData();
  }


  ngOnInit() {
    this.reportService.getBestSellers().subscribe(d => {
      this.bestSellers = d;
    });
  }


  dashboardData() {
    this.dashboardService.getDetails().subscribe(
      (response: any) => {
        this.ngxService.stop();
        this.data = response.data;
      },
      (error: any) => {
        this.ngxService.stop();
        console.log(error);
        if (error.error?.message) {
          this.responseMessage = error.error?.message;
        } else {
          this.responseMessage = GlobalConstants.genericError;
        }

        this.snackbarService.openSnackBar(
          this.responseMessage,
          GlobalConstants.error
        );
      }
    );
  }
}
