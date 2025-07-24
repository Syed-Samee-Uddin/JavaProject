import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Faq } from '../_model/faq.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FaqService {
  private apiUrl = 'http://localhost:8080/api/faqs';

  constructor(private http: HttpClient) {}

  getAllFaqs(): Observable<Faq[]> {
    return this.http.get<Faq[]>(this.apiUrl);
  }
}
