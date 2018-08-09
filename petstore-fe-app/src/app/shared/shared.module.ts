import { AlphabetTextValidatorDirective } from './alphabet-text-validator.directive';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [AlphabetTextValidatorDirective],
  exports: [AlphabetTextValidatorDirective]
})
export class SharedModule { }
