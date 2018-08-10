import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PetSearchByIdComponent } from './pet-search-by-id/pet-search-by-id.component';
import { PetDisplayFormComponent } from './pet-display-form/pet-display-form.component';
import { PetSearchComponent } from './pet-search.component';
import { PetListComponent } from './pet-list/pet-list.component';

const searchRoutes: Routes = [
  // {path: 'searchPets/petById', component: PetSearchByIdComponent, pathMatch: 'full' },
  // {path: 'searchPets/petsByCategory', redirectTo: '**',  pathMatch: 'full' },
  // {path: 'searchPets/petsByTags', redirectTo: '**', pathMatch: 'full' },
  // {path: 'searchPets/petsByStatus', redirectTo: '**',  pathMatch: 'full' },
  // {path: 'searchPets/petById/:id', component: PetDisplayFormComponent,  pathMatch: 'full' }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forRoot(searchRoutes)
  ],
  exports: [PetDisplayFormComponent],
  declarations: [ PetSearchByIdComponent, PetDisplayFormComponent, PetSearchComponent, PetListComponent]
})
export class PetSearchModule { }
