import { PetAdComponent } from './../pet-ad/pet-ad.component';
import { PetService } from './../pet.service';
import { Component, OnInit, Input, ViewChild, OnDestroy } from '@angular/core';
import { PetBannerDirective } from '../pet-banner.directive';
import { Pet } from '../pet';
import { ComponentFactoryResolver } from '@angular/core';


@Component({
  selector: 'app-pet-banner',
  templateUrl: './pet-banner.component.html',
  styleUrls: ['./pet-banner.component.css']
})
export class PetBannerComponent implements OnInit, OnDestroy {

  // @Input()
  pets: Pet[];
  direction: string;
  currentAdIndex = -1;
  @ViewChild(PetBannerDirective) petBanner: PetBannerDirective;
  interval: any;
  constructor(private componentFactoryResolver: ComponentFactoryResolver, private petService: PetService) { }

  ngOnInit() {
    this.petService.getAllPets().subscribe(
      allPets => {
        this.pets = allPets;
        this.loadComponent();
        this.getPets();
      }
    );
  }

  ngOnDestroy() {
    clearInterval(this.interval);
  }

  loadComponent() {
    this.currentAdIndex = (this.currentAdIndex + 1) % this.pets.length;
    const pet = this.pets[this.currentAdIndex];

    const componentFactory = this.componentFactoryResolver.resolveComponentFactory(PetAdComponent);

    const viewContainerRef = this.petBanner.viewContainerRef;
    viewContainerRef.clear();

    const componentRef = viewContainerRef.createComponent(componentFactory);
    (<PetAdComponent>componentRef.instance).pet = pet;
    (<PetAdComponent>componentRef.instance).direction = this.direction;

  }

  getPets() {
    this.interval = setInterval(() => {
      this.loadComponent();
    }, 3000);
  }
}
