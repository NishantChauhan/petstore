
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-find-pet',
  templateUrl: './find-pet.component.html',
  styleUrls: ['./find-pet.component.css']
})
export class FindPetComponent implements OnInit {
  petId: number;
  constructor() {}

  ngOnInit() {

  }
  onFindPetClick() {
    console.log('Button Clicked');
    this.petId=1
  }
}
