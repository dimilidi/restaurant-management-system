import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private router: Router) { }

  public isAuthenticated(): boolean {
    const token = localStorage.getItem('token');

    if(!token) {
      this.router.navigate(['/']);
      return false;
    }
    return true;
  }

  public getCurrentUser(): any {
    const token = localStorage.getItem('token');
    if (token) {
      try {
        const tokenPayload = jwtDecode(token);
        return tokenPayload;
      } catch (error) {
        localStorage.clear();
        this.router.navigate(['/']);
        return null;
      }
    }
    return null;
  }

  public getCurrentUserName(): string {
    const user = this.getCurrentUser();
    return user ? user.name : '';
  }
}
