import { RouterModule, Routes } from '@angular/router';
import { PetSearchPanelComponent } from './pet-search-panel/pet-search-panel.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PetSearchByIdComponent } from './pet-search-by-id/pet-search-by-id.component';
import { PetDisplayFormComponent } from './pet-display-form/pet-display-form.component';

const searchRoutes: Routes = [
  {path: 'searchPets/petById', component: PetSearchByIdComponent, pathMatch: 'full' },
  {path: 'searchPets/petsByCategory', redirectTo: '',  pathMatch: 'full' },
  {path: 'searchPets/petsByTags', redirectTo: '', pathMatch: 'full' },
  {path: 'searchPets/petsByStatus', redirectTo: '',  pathMatch: 'full' },
  {path: 'searchPets/petById/:id', component: PetDisplayFormComponent,  pathMatch: 'full' }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(searchRoutes)
  ],
  declarations: [ PetSearchByIdComponent, PetDisplayFormComponent, PetSearchPanelComponent]
})
export class PetSearchModule { }
