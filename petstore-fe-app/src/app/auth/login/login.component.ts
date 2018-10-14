import { Component, EventEmitter, Output } from '@angular/core';
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
  @Output()
  appUser = new EventEmitter<User>();
  loginStatus: ExecutionStatus;
  constructor(private authService: AuthService, private router: Router) {}

  login() {
    this.authService.obtainToken(this.user).subscribe(tokenExecutionStatus => {
      console.log( tokenExecutionStatus);
      this.loginStatus = tokenExecutionStatus;
      if (tokenExecutionStatus.status === 'Login Successful') {
        this.authService.getUser().subscribe(
          roleUser => {
          this.user = roleUser;
          this.appUser.emit(this.user);
          this.router.navigateByUrl('api/home');
          }
        );
      } else {
        this.appUser.emit(undefined);
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
