import { Directive, ViewContainerRef } from '@angular/core';

@Directive({
  selector: '[pet-banner-host]',
})
export class PetBannerDirective {
  constructor(public viewContainerRef: ViewContainerRef) { }
}
