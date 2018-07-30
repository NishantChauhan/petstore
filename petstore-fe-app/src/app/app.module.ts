import { PetSearchPanelComponent } from './pet-search/pet-search-panel/pet-search-panel.component';
import { PetSearchModule } from './pet-search/pet-search.module';
import { BrowserModule, enableDebugTools } from '@angular/platform-browser';
import { NgModule, Component } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { RouterModule, Routes } from '@angular/router';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { PetStoreHomeComponent } from './pet-store-home/pet-store-home.component';
import { PetKeeperModule } from './pet-keeper/pet-keeper.module';

// Define Routes in your application
// The router uses a first-match wins strategy when matching routes
const appRoutes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: PetStoreHomeComponent },
  {path: 'searchPets', component: PetSearchPanelComponent },
  {path: '**', component: PageNotFoundComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    PetStoreHomeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    PetSearchModule,
    PetKeeperModule,
    RouterModule.forRoot(appRoutes, {enableTracing: false})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
