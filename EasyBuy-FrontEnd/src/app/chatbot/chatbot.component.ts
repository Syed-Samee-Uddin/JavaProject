import { Component, ElementRef, ViewChild, AfterViewChecked, OnInit } from '@angular/core';
import { ChatbotService } from '../_services/chatbot.service';

@Component({
  selector: 'app-chatbot',
  templateUrl: './chatbot.component.html',
  styleUrls: ['./chatbot.component.css']
})
export class ChatbotComponent implements OnInit, AfterViewChecked {
  userInput: string = '';
  chatHistory: { question: string; answer: string }[] = [];
  loading = false;
  error = '';

  isChatbotOpen = false;

  @ViewChild('chatBox') chatBox!: ElementRef;
  @ViewChild('chatInput') chatInput!: ElementRef;


  constructor(private chatbotService: ChatbotService) {}

  ngOnInit(): void {}

  ngAfterViewChecked(): void {
    this.scrollToBottom();
      setTimeout(() => this.chatInput?.nativeElement.focus(), 0);

  }

  toggleChatbot() {
    this.isChatbotOpen = !this.isChatbotOpen;
  }

  sendMessage() {
    if (!this.userInput.trim()) return;

    const question = this.userInput.trim();
    this.chatHistory.push({ question, answer: '...' });
    this.userInput = '';
    setTimeout(() => this.chatInput?.nativeElement.focus(), 0);

    this.loading = true;
    this.error = '';

    this.chatbotService.askQuestion(question).subscribe({
      next: (response) => {
        this.chatHistory[this.chatHistory.length - 1].answer = response;
        this.loading = false;
      },
      error: () => {
        this.chatHistory[this.chatHistory.length - 1].answer = 'Error: Could not get response.';
        this.loading = false;
        this.error = 'Failed to connect to chatbot API.';
      }
    });
  }

  scrollToBottom() {
    try {
      this.chatBox.nativeElement.scrollTop = this.chatBox.nativeElement.scrollHeight;
    } catch (err) {}
  }
}



























// import { Component } from '@angular/core';
// import { HttpClient } from '@angular/common/http';

// @Component({
//   selector: 'app-chatbot',
//   templateUrl: './chatbot.component.html',
//   styleUrls: ['./chatbot.component.css']
// })
// export class ChatbotComponent {
//   userInput: string = '';
//   messages: { sender: string, text: string }[] = [];

//   constructor(private http: HttpClient) {}

//   sendMessage() {
//     if (!this.userInput.trim()) return;

//     // Add user message
//     this.messages.push({ sender: 'user', text: this.userInput });

//     // Send to backend
//     this.http.post<any>('http://localhost:8080/api/chatbot/ask', {
//       question: this.userInput
//     }).subscribe(response => {
//       this.messages.push({ sender: 'bot', text: response.answer });
//     }, error => {
//       this.messages.push({ sender: 'bot', text: 'Sorry, something went wrong.' });
//     });

//     this.userInput = '';
//   }
// }




























// export class ChatbotComponent implements OnInit {
//   userInput: string = '';
//   chatHistory: { question: string; answer: string }[] = [];
//   loading = false;
//   error = '';

//   constructor(private chatbotService: ChatbotService) {}

//   ngOnInit(): void {}

//   sendMessage() {
//     if (!this.userInput.trim()) {
//       return;
//     }
//     this.loading = true;
//     this.error = '';

//      isChatbotOpen = false; // 👈 added
//   toggleChatbot() {       // 👈 added
//     this.isChatbotOpen = !this.isChatbotOpen;
//   }

//     const question = this.userInput.trim();
//     this.chatHistory.push({ question, answer: '...' });
//     this.userInput = '';

//     this.chatbotService.askQuestion(question).subscribe({
//       next: (response) => {
//         // Replace last answer placeholder with real answer
//         this.chatHistory[this.chatHistory.length - 1].answer = response;
//         this.loading = false;
//       },
//       error: (err) => {
//         this.chatHistory[this.chatHistory.length - 1].answer = 'Error: Could not get response.';
//         this.loading = false;
//         this.error = 'Failed to connect to chatbot API.';
//       }
//     });
//   }
// }
