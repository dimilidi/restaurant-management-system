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
        this.dataSource = new MatTableDataSource(response.data);
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

  downloadFile(data: any) {
    console.log(data);

    this.billService.getPdf(data).subscribe(
      (response: any) => {
        console.log(response.data);

        saveAs(response, data.uuid + '.pdf');
        this.ngxService.stop();
      },
      (error: any) => {
        console.log(error.eror);
      }
    );
  }

  // downloadFile(data: any) {
  //   this.billService.getPdf(data).subscribe(
  //     (response: any) => {
  //       console.log(response);
  
  //       // Extract the base64 encoded PDF from the response
  //       const base64Data = response.data;
  
  //       // Convert base64 string to Blob
  //       const byteCharacters = atob(base64Data);
  //       const byteNumbers = new Array(byteCharacters.length);
  //       for (let i = 0; i < byteCharacters.length; i++) {
  //         byteNumbers[i] = byteCharacters.charCodeAt(i);
  //       }
  //       const byteArray = new Uint8Array(byteNumbers);
  //       const blob = new Blob([byteArray], { type: 'application/pdf' });
  
  //       // Use FileSaver.js to save the PDF
  //       saveAs(blob, data.uuid + '.pdf');
  //       this.ngxService.stop();
  //     },
  //     (error: any) => {
  //       console.log(error.error);
  //       this.ngxService.stop();
  //     }
  //   );
  // }
  
}
