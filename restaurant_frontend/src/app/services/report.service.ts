import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ReportService {
  url = environment.apiUrl;

  constructor(private httpClient: HttpClient) {}

  getBestSellers(): Observable<any> {
    return this.httpClient.get(`${this.url}/reports/best-sellers`);
  }

  getTopEmployees(): Observable<any> {
    return this.httpClient.get(`${this.url}/reports/top-employees`);
  }

  getRegularGuests(): Observable<any> {
    return this.httpClient.get(`${this.url}/reports/regular-guests`);
  }
}
