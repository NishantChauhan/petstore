import { PetService } from './../pet.service';
import { Pet } from '../pet';
import { Component } from '@angular/core';

@Component({
  selector: 'app-pet-form',
  templateUrl: './pet-form.component.html',
  styleUrls: ['./pet-form.component.css']
})
export class PetFormComponent {
  petId: number;
  selectedPet: Pet;
  submitted = false;

  constructor(private petService: PetService) {}


  onSubmit() {
    this.submitted = true;
    this.petService.getPet(this.petId).subscribe(pet => {

      // To test integration of Angular and SpringBoot
      // if (pet) {
      //   pet.photoUrls.forEach(photoUrl => {
      //     photoUrl.url = 'http://localhost:8080/' + photoUrl.url;
      //   });
      // }
      console.log(pet);
      this.selectedPet = pet;
    });
  }

  // TODO: Remove this when we're done

  get diagnostic() {
    return JSON.stringify(this.selectedPet);
  }
}
