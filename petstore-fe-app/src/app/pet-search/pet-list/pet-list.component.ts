import { PetService } from './../../pet.service';
import { Component, OnInit } from '@angular/core';
import { Pet } from '../../pet';

@Component({
  selector: 'app-pet-list',
  templateUrl: './pet-list.component.html',
  styleUrls: ['./pet-list.component.css']
})
export class PetListComponent implements OnInit {
  pets: Pet[];
  petGrid: Pet[][];
  constructor(private petService: PetService) {
    this.petService.getAllPets().subscribe(fetchedPets => {
      this.pets = fetchedPets;
      this.petGrid = [];
      for ( let i = 0 ; i < this.pets.length / 4 ; i++) {
        this.petGrid[i] = [];
        for (let j = 0; j < 4 ; j++) {
          this.petGrid[i][j] = this.pets[i * 4 + j];
        }
      }
    });
  }

  ngOnInit() {

  }
}
