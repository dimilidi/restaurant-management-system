import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { RegisterComponent } from '../register/register.component';
import { ForgotPasswordComponent } from '../forgot-password/forgot-password.component';
import { LoginComponent } from '../login/login.component';
import { UserService } from '../services/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ResetPasswordComponent } from '../reset-password/reset-password.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  constructor(
    private dialog: MatDialog,
    private userService: UserService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    // this.userService.checkToken().subscribe(
    //   (response: any) => {
    //     console.log(response);
        
    //     console.log(response.message);
        
    //     console.log(response.message == "VALID");
    //     if (response.message == "VALID") {
    //       this.router.navigate(['/restaurant/dashboard']);
    //     }
    //   },
    //   (error: any) => {
    //     console.log(error);
    //   }
    // );

    // Open dialog if token is present in the URL
    this.route.queryParams.subscribe((params) => {
      const token = params['token'];
      if (token) {
        this.dialog.open(ResetPasswordComponent, {
          width: '400px',
          data: { token: token },
        });
        // Clear token from URL to avoid opening the dialog again
        this.router.navigate([], {
          queryParams: { token: null },
          queryParamsHandling: 'merge',
        });
      }
    });
  }

  handleRegisterAction() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '550px';
    this.dialog.open(RegisterComponent, dialogConfig);
  }

  handleForgotPasswordAction() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '550px';
    this.dialog.open(ForgotPasswordComponent, dialogConfig);
  }

  handleLoginAction() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '550px';
    this.dialog.open(LoginComponent, dialogConfig);
  }
}
