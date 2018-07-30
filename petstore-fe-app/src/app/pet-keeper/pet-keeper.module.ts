import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddPetComponent } from './add-pet/add-pet.component';
import { FormsModule} from '../../../node_modules/@angular/forms';

const keeperRoutes: Routes = [
  // Path cannot start with a slash
  {path: 'addPets', component: AddPetComponent, pathMatch: 'full' },
  {path: 'updatePets', redirectTo: '',  pathMatch: 'full' },
  {path: 'deletePets', redirectTo: '', pathMatch: 'full' },
];
@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(keeperRoutes)
  ],
  declarations: [AddPetComponent]
})
export class PetKeeperModule { }
