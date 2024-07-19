import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {
  resetPasswordForm: any =  FormGroup;
  token: any;
  errorMessage: any;

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    public dialogRef: MatDialogRef<ResetPasswordComponent>,
    private router: Router,
    @Inject(MAT_DIALOG_DATA) public data: { token: string }
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
          console.log('Password reset successful');
          this.dialogRef.close();
          this.router.navigate(['/login']);
        },
        error => {
          console.error('Password reset failed', error);
          if (error.status === 400 && error.error.message) {
            this.errorMessage = error.error.message;
          } else {
            this.errorMessage = 'Password reset failed. Please try again.';
          }
        }
      );
    }
  }
}