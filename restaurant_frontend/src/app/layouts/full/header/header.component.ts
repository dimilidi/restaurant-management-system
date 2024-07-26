import { Component } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ChangePasswordComponent } from 'src/app/material-component/dialog/change-password/change-password.component';
import { ConfirmationComponent } from 'src/app/material-component/dialog/confirmation/confirmation.component';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
})
export class HeaderComponent {
  role: any;
  username: string = this.authService.getCurrentUserName();
  constructor(
    private router: Router,
    private dialog: MatDialog,
    private authService: AuthService
  ) {}

  logout() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.data = {
      message: 'Logout',
      confirmation: true,
    };

    const dialogRef = this.dialog.open(ConfirmationComponent, dialogConfig);
    const sub = dialogRef.componentInstance.onEmitStatusChange.subscribe(
      (response) => {
        dialogRef.close();
        localStorage.clear();
        this.router.navigate(['/']);
      }
    );
  }

  changePassword() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '550px';
    this.dialog.open(ChangePasswordComponent, dialogConfig);
  }
}
