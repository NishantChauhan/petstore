import { Pet } from './../pet';
import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import {
  trigger,
  state,
  style,
  animate,
  transition
} from '@angular/animations';

@Component({
  selector: 'app-pet-ad',
  templateUrl: './pet-ad.component.html',
  styleUrls: ['./pet-ad.component.css'],
  animations: [
    trigger('compState', [
      state('right', style({transform: 'translateX(0)'})),
      state('left', style({transform: 'translateX(100)'})),
      // transition('void => *', [
      //   style({transform: 'translateX(-100%)'}),
      //   animate(100)
      // ]),
      transition('* => void', [
        animate(100, style({transform: 'translateX(100%)'}))
      ])
    ])
  ]
})
export class PetAdComponent implements OnInit, OnDestroy {
  @Input() pet: Pet;
  live = false;
  @Input() direction: string;

  constructor() { }

  ngOnInit() {
    this.live = true;
  }

  ngOnDestroy() {
    this.live = false;
  }

}
