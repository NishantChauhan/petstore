import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';

@NgModule({
  imports: [
    CommonModule, FormsModule
  ],
  declarations: [LoginComponent, LogoutComponent],
  exports: [LoginComponent]
})
export class AuthModule { }
