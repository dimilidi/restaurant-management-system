import { Component, OnInit } from '@angular/core';
import { BillService } from '../services/bill.service';
import { MatTableDataSource } from '@angular/material/table';
import { ProductService } from '../services/product.service';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { SnackbarService } from '../services/snackbar.service';
import { GlobalConstants } from '../shared/global-constants';

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
    private billService: BillService,
    private ngxService: NgxUiLoaderService,
    private snackbarService: SnackbarService,
  ) {}

// ngOnInit() {
//   this.billService.getRegularGuests().subscribe(
//     (data) => {
//       this.dataSource = data;
//     },
//     (error: any) => {
//       console.error('Error fetching regular guests:', error);
//     }
//   );
  // }

  ngOnInit(): void {
    this.ngxService.start();
    this.tableData();
  }

  tableData() {
    this.billService.getRegularGuests().subscribe(
      (response: any) => {
        this.ngxService.stop();
        this.dataSource = new MatTableDataSource(response);
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
