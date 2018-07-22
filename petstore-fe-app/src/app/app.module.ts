import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { FindPetComponent } from './find-pet/find-pet.component';
import { DisplayPetComponent } from './display-pet/display-pet.component';

@NgModule({
  declarations: [
    AppComponent,
    FindPetComponent,
    DisplayPetComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
