import { FindPetComponent } from './../find-pet/find-pet.component';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-display-pet',
  templateUrl: './display-pet.component.html',
  styleUrls: ['./display-pet.component.css']
})
export class DisplayPetComponent implements OnInit {
  @Input('petId') petId;
  constructor() { }

  ngOnInit() {
    console.log(this.petId);
  }

}
