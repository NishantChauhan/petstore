import { Component } from '@angular/core';
import { AuthService } from './auth/auth.service';
import { User } from './auth/user';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  user: User;
  title = 'app';
  authenticated: boolean;
  loggedout: boolean;
  constructor(private authservice: AuthService) {
    this.authenticated = authservice.getUserStatus();
    if (this.authenticated) {
      this.user = authservice.getUserDetails();
      this.loggedout = !this.authenticated;
    }
  }

  login(appUser: User) {
    this.user = appUser;
    this.authenticated = appUser ? true : false;
  }
  logout() {
    this.loggedout = true;
    this.user = undefined;
  }
}
