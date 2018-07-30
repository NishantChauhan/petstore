import { PetService } from './../../pet.service';
import { Mode } from '../../mode';
import { Pet } from '../../pet';
import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '../../../../node_modules/@angular/router';
import {Location} from '@angular/common';

@Component({
  selector: 'app-pet-display-form',
  templateUrl: './pet-display-form.component.html',
  styleUrls: ['./pet-display-form.component.css']
})
export class PetDisplayFormComponent implements OnInit {
  @Input() selectedPet: Pet; // @ Input to ge tthis property from parent component
  @Input() mode: Mode;

  constructor(
    private route: ActivatedRoute,
    private petService: PetService,
    private location: Location
  ) { }

  ngOnInit() {
    this.getPet();
  }

  getPet() {
    const id = +this.route.snapshot.paramMap.get('id'); // to get the parameter from the route
    this.petService.getPet(id).subscribe(pet => this.selectedPet = pet);
  }

}
