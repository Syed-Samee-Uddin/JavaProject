import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'jwt-youtube-ui';

    showChatbot: boolean = false;
toggleChatbot() {
    this.showChatbot = !this.showChatbot;
  }
}
