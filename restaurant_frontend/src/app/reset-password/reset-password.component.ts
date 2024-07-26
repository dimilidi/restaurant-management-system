import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog, MatDialogConfig, MatDialogRef } from '@angular/material/dialog';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoginComponent } from '../login/login.component';
import { SnackbarService } from '../services/snackbar.service';
import { GlobalConstants } from '../shared/global-constants';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {
  resetPasswordForm: any =  FormGroup;
  token: any;
  responseMessage: any;

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    public dialogRef: MatDialogRef<ResetPasswordComponent>,
    private router: Router,
    private snackbarService: SnackbarService,
    private snackBar: MatSnackBar,
    private dialog: MatDialog,
    @Inject(MAT_DIALOG_DATA) public data: { token: string}
  ) {}

  ngOnInit(): void {
    this.resetPasswordForm = this.fb.group({
      newPassword: ['', [Validators.required, Validators.minLength(6)]]
    });

    this.token = this.data.token;

  }

  handleSubmit() {
    if (this.resetPasswordForm.valid) {
      const newPassword = this.resetPasswordForm.get('newPassword').value;
      this.userService.resetPassword(this.token, newPassword).subscribe(
        response => {
          this.dialogRef.close();
          this.responseMessage = response.message || 'Password reset successful';
          console.log('Password reset successful');
          this.snackbarService.openSnackBar(
            this.responseMessage,
            'success'
          );
          this.handleLoginAction();
        },
        error => {
          console.error('Password reset failed', error);
          if (error.status === 400 && error.error.message) {
            this.responseMessage = error.error.message;
          } else {
            this.responseMessage = 'Password reset failed. Please try again.';
          }
        this.snackbarService.openSnackBar(
            this.responseMessage,
            GlobalConstants.error
          );
        });
          
      
    }
  }

  handleLoginAction() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '550px';
    this.dialog.open(LoginComponent, dialogConfig);
  }
}