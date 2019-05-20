import { Location } from '@angular/common';
import { PetService } from '../../pet.service';
import { Mode } from '../../mode';
import { Pet } from '../../pet';
import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-pet-display-form',
  templateUrl: './pet-display-form.component.html',
  styleUrls: ['./pet-display-form.component.css']
})
export class PetDisplayFormComponent implements OnInit {
  loaded = false;
  @Input()
  id: number; // @ Input to get this property from parent component
  @Input()
  mode: Mode;
  @Input()
  displayedPet: Pet;
  url: string;
  @Output()
  deleteRequest = new EventEmitter<Pet>();
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private petService: PetService,
    private location: Location
  ) {}

  ngOnInit() {
    this.getPet();
  }

  getPet() {
    if (!this.displayedPet) {
      if (!this.id) {
        this.id = +this.route.snapshot.paramMap.get('id'); // to get the parameter from the route
      }
      this.petService.getPet(this.id).subscribe(pet => {
        this.displayedPet = pet;
        if (this.displayedPet && this.displayedPet.photoUrls[0]) {
          this.url = this.displayedPet.photoUrls[0].url;
        } else {
          this.url = 'assets/BrokenImage.jpg';
        }
        this.loaded = true;
      });
    }
  }

  editPet() {
    this.router.navigate(['api/editPetById', this.displayedPet.id]);
  }

  deletePet() {
    if (
      confirm(
        'Are you sure to delete ' +
          this.displayedPet.name +
          '[ id = ' +
          this.displayedPet.id +
          ']' +
          '?'
      )
    ) {
      this.deleteRequest.emit(this.displayedPet);
      this.router.navigate(['api/searchPets']);
    }
  }
}
