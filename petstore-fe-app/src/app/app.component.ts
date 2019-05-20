import { Component } from '@angular/core';
import { User } from './auth/user';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  user: User;
  title = 'app';
  authenticated = false;
  loggedout: boolean;

  constructor() {}

  login(appUser: User) {
    this.user = appUser;
    this.authenticated = appUser ? true : false;
  }
  logout() {
    this.loggedout = true;
    this.user = undefined;
  }
}
