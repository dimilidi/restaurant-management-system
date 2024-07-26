import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { SnackbarService } from './snackbar.service';
import { jwtDecode } from 'jwt-decode';
import { GlobalConstants } from '../shared/global-constants';

@Injectable({
  providedIn: 'root',
})
export class RouteGuardService {

  isAdmin: boolean = false;
  constructor(
    public authService: AuthService,
    public router: Router,
    private snackbarServise: SnackbarService
  ) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const token: any = localStorage.getItem('token');
    var tokenPayload: any;
    try {
      tokenPayload = jwtDecode(token);      
    } catch (error) {
      localStorage.clear();
      if (route.routeConfig?.path === '') {
        return true; // Allow access to home if token is invalid or expired
      }
      this.router.navigate(['/']);
      return false; // Redirect to home if token is invalid
    }

    if (this.authService.isAuthenticated()) {
      if (route.routeConfig?.path === '') {
        this.router.navigate(['/restaurant/dashboard']);
        return false; // Prevent access to home if user is logged in
      }

      const expectedRoles: string[] = route.data['expectedRole'];
      const userRoles = tokenPayload.roles.map((role: string) => role.replace('ROLE_', ''));
      const roleMatch = userRoles.some((role: string) => expectedRoles.includes(role));
      this.isAdmin = userRoles.includes("ADMIN");
      //console.log("NAME USER -> " + JSON.stringify(tokenPayload.name));
      
      if (roleMatch) {
        return true; // Allow access if user role matches expected roles
      } else {
        this.snackbarServise.openSnackBar(
          GlobalConstants.unauthorized,
          GlobalConstants.error
        );
        this.router.navigate(['/restaurant/dashboard']);
        return false; // Redirect to dashboard if user role does not match
      }
    } else {
      if (route.routeConfig?.path !== '') {
        this.router.navigate(['/']);
        return false; // Redirect to home if user is not authenticated
      }
      return true; // Allow access to home if user is not authenticated
    }
  }
}

