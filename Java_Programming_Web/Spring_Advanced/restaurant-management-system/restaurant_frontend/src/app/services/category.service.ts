import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  url = environment.apiUrl;

  constructor(private httpClient: HttpClient) {}

  add(data: any) {
    return this.httpClient.post(this.url + '/categories/add', data, {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
    });
  }

  update(data: any) {
    return this.httpClient.post(this.url + '/categories/update', data, {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
    });
  }

  getCategories() {
    return this.httpClient.get(this.url + '/categories/get');
  }

  getCategoriesWithActiveProducts() {
    return this.httpClient.get(this.url + '/categories/filter');
  }

  getCategoryById(id: number): Observable<any> {
    return this.httpClient.get<any>(`${this.url}/${id}`);
  }

  getFilteredCategories() {
    return this.httpClient.get(this.url + '/categories/get?filterValue=true');
  }

  deleteCategory(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.url}/categories/delete/${id}`);
  }
}
