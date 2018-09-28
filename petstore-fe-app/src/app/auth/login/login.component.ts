import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './../auth.service';
import { ExecutionStatus } from './../execution-status';
import { User } from './../user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  user = new User();
  loginStatus: ExecutionStatus;
  @Output()
  isAuthenticated = new EventEmitter<boolean>();
  constructor(private authService: AuthService, private router: Router) {}

  login() {
    this.authService.login(this.user).subscribe(executionStatus => {
      this.loginStatus = executionStatus;
      if (executionStatus.status === 'Login Successful') {
        this.isAuthenticated.emit(true);
        this.router.navigateByUrl('/home');
      } else {
        this.isAuthenticated.emit(false);
      }
    });
  }
  clear() {
    this.loginStatus = undefined;
  }
  // getDiagonstics(): User {
  //   return this.user;
  // }
}
