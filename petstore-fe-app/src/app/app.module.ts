import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { AuthModule } from './auth/auth.module';
import { LoginComponent } from './auth/login/login.component';
import { LogoutComponent } from './auth/logout/logout.component';
import { CacheInterceptorService } from './cache-interceptor.service';
import { MaintenanceComponent } from './maintenance/maintenance.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { PetAdComponent } from './pet-ad/pet-ad.component';
import { PetBannerDirective } from './pet-banner.directive';
import { PetBannerComponent } from './pet-banner/pet-banner.component';
import { PetHomeComponent } from './pet-home/pet-home.component';
import { AddPetComponent } from './pet-keeper/add-pet/add-pet.component';
import { EditPetComponent } from './pet-keeper/edit-pet/edit-pet.component';
import { PetKeeperModule } from './pet-keeper/pet-keeper.module';
import { PetDisplayFormComponent } from './pet-search/pet-display-form/pet-display-form.component';
import { PetListComponent } from './pet-search/pet-list/pet-list.component';
import { PetSearchByIdComponent } from './pet-search/pet-search-by-id/pet-search-by-id.component';
import { PetSearchComponent } from './pet-search/pet-search.component';
import { PetSearchModule } from './pet-search/pet-search.module';

// Define Routes in your application
// The router uses a first-match wins strategy when matching routes
const appRoutes: Routes = [
  // { path: '', redirectTo: 'api/logon', pathMatch: 'full' },
  { path: '', redirectTo: 'api/home', pathMatch: 'full' },
  { path: 'api/home', component: PetHomeComponent, pathMatch: 'full' },
  { path: 'api/logon', component: LoginComponent, pathMatch: 'full' },
  { path: 'api/logoutSuccess', component: LogoutComponent, pathMatch: 'full' },
  {
    path: 'api/searchPets',
    component: PetSearchComponent,
    children: [
      {
        path: 'petById',
        component: PetSearchByIdComponent,
        pathMatch: 'full',
        children: [
          { path: ':id', component: PetDisplayFormComponent, pathMatch: 'full' },
        ],
      },
      {
        path: 'petsByCategory',
        component: MaintenanceComponent,
        pathMatch: 'full',
      },
      {
        path: 'petsByTags',
        component: MaintenanceComponent,
        pathMatch: 'full',
      },
      {
        path: 'petsByStatus',
        component: MaintenanceComponent,
        pathMatch: 'full',
      },
      {
        path: 'petById/:id',
        component: PetDisplayFormComponent,
        pathMatch: 'full',
      },
    ],
  },
  { path: 'api/addPets', component: AddPetComponent },
  { path: 'api/editPetById/:id', component: EditPetComponent },
  { path: 'api/listPets', component: PetListComponent, pathMatch: 'full' },
  { path: 'api/**', component: PageNotFoundComponent },
];

@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    MaintenanceComponent,
    PetHomeComponent,
    PetBannerDirective,
    PetBannerComponent,
    PetAdComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    PetSearchModule,
    PetKeeperModule,
    AuthModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(appRoutes, { enableTracing: false }),
  ],
  entryComponents: [PetAdComponent],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: CacheInterceptorService, multi: true },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
