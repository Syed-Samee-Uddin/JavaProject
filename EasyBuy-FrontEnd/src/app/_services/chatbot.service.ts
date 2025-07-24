import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChatbotService {

  private chatbotApiUrl = 'http://localhost:8080/api/chatbot/ask';
  private faqApiUrl = 'http://localhost:8080/api/faqs';

  constructor(private http: HttpClient) {}

  askQuestion(question: string): Observable<string> {
    return this.http.post(this.chatbotApiUrl, question, { responseType: 'text' });
  }

  getFaqs(): Observable<any[]> {
    return this.http.get<any[]>(this.faqApiUrl);
  }
}
