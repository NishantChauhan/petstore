import { Pet } from '../pet';
import { Component } from '@angular/core';

@Component({
  selector: 'app-pet-form',
  templateUrl: './pet-form.component.html',
  styleUrls: ['./pet-form.component.css']
})
export class PetFormComponent {
  petId: number;

  pets: Pet[] =
  [new Pet( 1, 'Tom', 'pussy-cat', ['../../assets/images/Tom.png'], ['cartoon', 'loves-milk'], 'AVAILABLE' ),
  new Pet( 2, 'Jerry', 'house-mouse', ['../../assets/images/Jerry.png'], ['cartoon', 'loves-cheese'], 'PENDING' ),
  new Pet( 3, 'Spike', 'bull-dog', ['../../assets/images/Spike.png'], ['cartoon', 'hates-cat'], 'SOLD' )
    ];

  selectedPet: Pet;

  submitted = false;

  onSubmit() {
    this.submitted = true;
    this.selectedPet = this.pets[this.petId - 1];

  }

  // TODO: Remove this when we're done

  get diagnostic() {
    return JSON.stringify(this.selectedPet);
  }
}
