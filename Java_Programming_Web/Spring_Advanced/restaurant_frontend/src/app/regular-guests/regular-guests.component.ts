import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { SnackbarService } from '../services/snackbar.service';
import { GlobalConstants } from '../shared/global-constants';
import { ReportService } from '../services/report.service';

@Component({
  selector: 'app-regular-guests',
  templateUrl: './regular-guests.component.html',
  styleUrls: ['./regular-guests.component.css'],
})
export class RegularGuestsComponent implements OnInit {
  displayedColumns: string[] = ['name', 'email', 'topProducts', 'billCount'];
  dataSource: any;
  responseMessage: any;

  constructor(
    private reportService: ReportService,
    private ngxService: NgxUiLoaderService,
    private snackbarService: SnackbarService,
  ) {}

  ngOnInit(): void {
    this.ngxService.start();
    this.tableData();
  }

  tableData() {
    this.reportService.getRegularGuests().subscribe(
      (response: any) => {
        this.ngxService.stop();
        this.dataSource = new MatTableDataSource(response.data);
        console.log(response);
        
      },
      (error: any) => {
        this.ngxService.stop();
        console.log(error.error?.message);
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

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLocaleLowerCase();
  }
}
