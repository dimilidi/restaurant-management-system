import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  url = environment.apiUrl;

  constructor(private httpClient: HttpClient) {}

register(data: any) {
  return this.httpClient.post(this.url + '/auth/register', data, {
    headers: new HttpHeaders().set('Content-Type', 'application/json'),
  });
}

  login(data: any) {
    return this.httpClient.post(this.url + '/auth/login', data, {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
    });
  }

  // checkToken() {
  //   return this.httpClient.get(this.url + '/users/checkToken');
  // }

  changePassword(data: any) {
    return this.httpClient.post(this.url + '/users/changePassword', data, {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
    });
  }

  forgotPassword(data: any) {
    return this.httpClient.post(this.url + '/password/forgot', data, {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
    });
  }

  resetPassword(token: string, newPassword: string): Observable<any> {
    return this.httpClient.post(`${this.url}/password/reset`, {
      token,
      newPassword,
    });
  }

  getUsers() {
    return this.httpClient.get(this.url + '/users/get');
  }

  update(data: any) {
    return this.httpClient.post(this.url + '/users/update', data, {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
    });
  }

  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'An unknown error occurred!';
    if (error.error instanceof ErrorEvent) {
      // Client-side or network error
      errorMessage = `Client-side error: ${error.error.message}`;
    } else {
      // Server-side error
      switch (error.status) {
        case 0:
          errorMessage = 'Server is down. Please try again later.';
          break;
        case 400:
          errorMessage = `Bad Request: ${error.error.message}`;
          break;
        case 401:
          errorMessage = `Unauthorized: ${error.error.message}`;
          break;
        case 404:
          errorMessage = `Not Found: ${error.error.message}`;
          break;
        case 500:
          errorMessage = `Internal Server Error: ${error.error.message}`;
          break;
        default:
          errorMessage = `Server returned code ${error.status}, body was: ${error.error}`;
      }
    }
    return throwError(() => new Error(errorMessage));
  }
}
