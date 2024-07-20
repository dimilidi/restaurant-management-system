import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { SnackbarService } from '../services/snackbar.service';
import { MatDialogRef,  MatDialog } from '@angular/material/dialog';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { GlobalConstants } from '../shared/global-constants';
import { RegisterComponent } from '../register/register.component';
import { ForgotPasswordComponent } from '../forgot-password/forgot-password.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  hide = true;
  loginForm: any = FormGroup;
  responseMessage: any;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService,
    private snackbarService: SnackbarService,
    public dialogRef: MatDialogRef<LoginComponent>,
    private dialog: MatDialog,
    private ngxService: NgxUiLoaderService
  ) {}

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: [
        null,
        [Validators.required, Validators.pattern(GlobalConstants.emailRegex)],
      ],
      password: [null, [Validators.required]],
    });
  }

  handleSubmit() {
    this.ngxService.start();
    var formData = this.loginForm.value;
    var data = {
      email: formData.email,
      password: formData.password,
    };

    this.userService.login(data).subscribe(
      (response: any) => {
        this.ngxService.stop();
        this.dialogRef.close();
        localStorage.setItem('token', response.data);
        this.router.navigateByUrl('/restaurant/dashboard');
      },
      (error) => {
        console.log(error);
        this.ngxService.stop();

        if (error.status === 400 || error.status === 401 ) {
          const errors = error.error;
          console.log(errors);

          Object.keys(errors).forEach((field) => {
            const control = this.loginForm.get(field);
            if (control) {
              console.log(errors[field]);
              control.setErrors({ serverError: errors[field] });
            } else{
              this.responseMessage = errors.message;
            }
            
          });
        } else {
          this.responseMessage =
            error.error?.message || GlobalConstants.genericError;   
     
        }
            this.snackbarService.openSnackBar(
          this.responseMessage,
          GlobalConstants.error
        );
      }
    );
  }

  openRegisterDialog() {
    this.dialogRef.close(); 
    this.dialog.open(RegisterComponent, {
      width: '600px',
    });
  }

  openForgottenPasswordDialog() {
    this.dialogRef.close(); 
    this.dialog.open(ForgotPasswordComponent, {
      width: '600px',
    });
  }
}
