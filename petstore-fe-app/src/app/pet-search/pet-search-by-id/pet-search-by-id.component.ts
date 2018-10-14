import { Mode } from '../../mode';
import { PetService } from '../../pet.service';
import { Pet } from '../../pet';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../auth/auth.service';

@Component({
  selector: 'app-pet-search-by-id',
  templateUrl: './pet-search-by-id.component.html',
  styleUrls: ['./pet-search-by-id.component.css']
})
export class PetSearchByIdComponent implements OnInit {
  petId: number;
  submitted = false;
  mode = Mode.READ_ONLY;

  constructor(
    private petService: PetService,
    private authServce: AuthService
  ) {}

  ngOnInit() {
    this.authServce.getUser().subscribe(user => {
      this.mode =
        user && user.role && user.role === 'ADMIN' ? Mode.EDIT : Mode.READ_ONLY;
    });
  }

  onSubmit() {
    this.submitted = true;
  }

  onKeyUp() {
    this.submitted = false;
  }

  deleteSelectedPet(emittedPet: Pet) {
    this.petService.deletePet(emittedPet.id).subscribe(message => {
      alert(message);
    });
  }
}
