import { SharedModule } from './../shared/shared.module';
import { PetSearchModule } from '../pet-search/pet-search.module';
import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddPetComponent } from './add-pet/add-pet.component';
import { FormsModule} from '@angular/forms';
import { EditPetComponent } from './edit-pet/edit-pet.component';

const keeperRoutes: Routes = [
  // Path cannot start with a slash
  // {path: 'addPets', component: AddPetComponent, pathMatch: 'full' },
  // {path: 'updatePets', redirectTo: '',  pathMatch: 'full' },
  // {path: 'deletePets', redirectTo: '', pathMatch: 'full' },
];
@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    PetSearchModule,
    SharedModule,
    RouterModule.forChild(keeperRoutes)
  ],
  declarations: [AddPetComponent, EditPetComponent]
})
export class PetKeeperModule { }
