import { CacheInterceptorService } from './cache-interceptor.service';
import { EditPetComponent } from './pet-keeper/edit-pet/edit-pet.component';
import { PetListComponent } from './pet-search/pet-list/pet-list.component';
import { PetDisplayFormComponent } from './pet-search/pet-display-form/pet-display-form.component';
import { PetSearchByIdComponent } from './pet-search/pet-search-by-id/pet-search-by-id.component';
import { PetSearchComponent } from './pet-search/pet-search.component';
import { PetSearchModule } from './pet-search/pet-search.module';
import { BrowserModule, enableDebugTools } from '@angular/platform-browser';
import { NgModule, Component } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppComponent } from './app.component';
import { RouterModule, Routes } from '@angular/router';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { PetKeeperModule } from './pet-keeper/pet-keeper.module';
import { AddPetComponent } from './pet-keeper/add-pet/add-pet.component';
import { MaintenanceComponent } from './maintenance/maintenance.component';
import { PetHomeComponent } from './pet-home/pet-home.component';


// Define Routes in your application
// The router uses a first-match wins strategy when matching routes
const appRoutes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: PetHomeComponent, pathMatch: 'full' },
  {
    path: 'searchPets',
    component: PetSearchComponent,
    children: [
      {
        path: 'petById',
        component: PetSearchByIdComponent,
        pathMatch: 'full',
        children: [
          { path: ':id', component: PetDisplayFormComponent, pathMatch: 'full' }
        ]
      },
      {
        path: 'petsByCategory',
        component: MaintenanceComponent,
        pathMatch: 'full'
      },
      {
        path: 'petsByTags',
        component: MaintenanceComponent,
        pathMatch: 'full'
      },
      {
        path: 'petsByStatus',
        component: MaintenanceComponent,
        pathMatch: 'full'
      },
      {
        path: 'petById/:id',
        component: PetDisplayFormComponent,
        pathMatch: 'full'
      }
    ]
  },
  { path: 'addPets', component: AddPetComponent },
  { path: 'editPetById/:id', component: EditPetComponent },
  { path: 'listPets', component: PetListComponent, pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    MaintenanceComponent,
    PetHomeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    PetSearchModule,
    PetKeeperModule,
    RouterModule.forRoot(appRoutes, { enableTracing: false })
  ],
  providers: [    { provide: HTTP_INTERCEPTORS, useClass: CacheInterceptorService, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule {}
