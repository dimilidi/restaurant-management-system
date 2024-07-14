import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TranslateLoader } from '@ngx-translate/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CustomLoaderService implements TranslateLoader {
  url = environment.apiUrl;

  constructor(private http: HttpClient) {}

  getTranslation(lang: string): Observable<any> {
    return this.http.get(`${this.url}/messages?lang=${lang}`, {
      headers: { 'Accept-Language': lang }
    });
    
  }
}
