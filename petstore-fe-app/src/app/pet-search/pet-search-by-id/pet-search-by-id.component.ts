import { Mode } from '../../mode';
import { PetService } from '../../pet.service';
import { Pet } from '../../pet';
import { Component} from '@angular/core';

@Component({
  selector: 'app-pet-search-by-id',
  templateUrl: './pet-search-by-id.component.html',
  styleUrls: ['./pet-search-by-id.component.css']
})
export class PetSearchByIdComponent {
  petId: number;
  selectedPet: Pet;
  submitted = false;
  mode = Mode.READ_ONLY;

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
