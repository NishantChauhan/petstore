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
  mode = Mode.EDIT;

  constructor(private petService: PetService) {}


  onSubmit() {
      this.submitted = true;
  }

  onKeyUp() {
    this.submitted = false;
  }

  // TODO: Remove this when we're done

  get diagnostic() {
    return JSON.stringify(this.selectedPet);
  }
}
