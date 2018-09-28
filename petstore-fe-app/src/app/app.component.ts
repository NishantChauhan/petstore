import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  authenticated = false;
  loggedout: boolean;

  constructor() {}

  login(isLoggedIn: boolean) {
    this.authenticated = isLoggedIn;
  }
  logout() {
    this.loggedout = true;
  }
}
