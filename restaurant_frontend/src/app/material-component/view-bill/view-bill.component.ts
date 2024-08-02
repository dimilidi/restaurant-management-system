import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { BillService } from 'src/app/services/bill.service';
import { SnackbarService } from 'src/app/services/snackbar.service';
import { GlobalConstants } from 'src/app/shared/global-constants';
import { ViewBillProductsComponent } from '../dialog/view-bill-products/view-bill-products.component';
import { ConfirmationComponent } from '../dialog/confirmation/confirmation.component';
import { saveAs } from 'file-saver';
import { RouteGuardService } from 'src/app/services/route-guard.service';

@Component({
  selector: 'app-view-bill',
  templateUrl: './view-bill.component.html',
  styleUrls: ['./view-bill.component.css'],
})
export class ViewBillComponent implements OnInit {
  displayedColumns: string[] = [
    'name',
    'email',
    'createdDate',
    'paymentMethod',
    'total',
    'view',
  ];
  dataSource: any;
  responseMessage: any;
  isAdmin: boolean = this.routeGuaredService.isAdmin;

  constructor(
    private billService: BillService,
    private ngxService: NgxUiLoaderService,
    private dialog: MatDialog,
    private snackbarService: SnackbarService,
    private router: Router,
    private routeGuaredService: RouteGuardService
  ) {}

  ngOnInit(): void {
    this.ngxService.start();
    this.tableData();
  }

  tableData() {
    this.billService.getBills().subscribe(
      (response: any) => {
        this.ngxService.stop();
        console.log('Backend response:', response.data); // Log backend data

        this.dataSource = new MatTableDataSource(
          response.data.map((bill: any) => {
            console.log('Original createdDate:', bill.createdDate); // Log each createdDate
            return {
              ...bill,
              createdDate: new Date(bill.createdDate * 1000), // Convert to milliseconds
            };
          })
        );
        console.log('DataSource:', this.dataSource.data); // Log processed dataSource
        
      },
      (error) => {
        this.ngxService.stop();
        console.log(error);

        if (error.eror?.message) {
          this.responseMessage = error.eror?.message;
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

  handleViewAction(values: any) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.data = {
      data: values,
    };
    dialogConfig.width = '100%';
    const dialogRef = this.dialog.open(ViewBillProductsComponent, dialogConfig);
    this.router.events.subscribe(() => {
      dialogRef.close();
    });
  }

  handleDeleteAction(values: any) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.data = {
      message: 'delete ' + values.name + ' bill',
      confirmation: true,
    };
    const dialogRef = this.dialog.open(ConfirmationComponent, dialogConfig);
    const sub = dialogRef.componentInstance.onEmitStatusChange.subscribe(
      (response) => {
        this.ngxService.start();
        this.deleteBill(values.id);
        dialogRef.close();
      }
    );
  }

  deleteBill(id: any) {
    return this.billService.delete(id).subscribe(
      (response: any) => {
        this.ngxService.stop();
        this.tableData();
        this.responseMessage = response?.message;
        this.snackbarService.openSnackBar(this.responseMessage, 'success');
      },
      (error) => {
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

  downloadReportAction(values: any) {
    this.ngxService.start();
    var data = {
      uuid: values.uuid,
    };
    this.downloadFile(data);
  }

// downloadFile(data: any) {
//   console.log(data);

//   this.billService.getPdf(data).subscribe(
//     (response: any) => {
//       console.log(response);

//       saveAs(response, data.uuid + '.pdf');
//       this.ngxService.stop();
//     },
//     (error: any) => {
//       console.log(error.error);
//     }
//   );
// }

downloadFile(data: any) {
  console.log(data);

  this.billService.getPdf(data).subscribe(
    (response: Blob) => {  // Ensure the response is treated as a Blob
      console.log(response);

      const file = new Blob([response], { type: 'application/pdf' });  // Create a new Blob object
      saveAs(file, data.uuid + '.pdf');  // Use file-saver to save the file
      this.ngxService.stop();
    },
    (error: any) => {
      console.log(error.error);
      this.ngxService.stop();  // Stop loader in case of error
      this.snackbarService.openSnackBar(GlobalConstants.genericError, GlobalConstants.error);
    }
  );
}

  
}
