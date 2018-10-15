import { AuthService } from './../auth.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {
  logoutSuccess = false;
  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit() {
    this.authService.logout().subscribe(executionStatus => {
      this.logoutSuccess = true;
    });
  }
  loginPage() {
    window.location.href = '/';
  }
}
