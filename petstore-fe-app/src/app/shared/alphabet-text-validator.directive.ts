import { Directive, Input } from '@angular/core';
import { NG_VALIDATORS, AbstractControl, Validator, ValidatorFn } from '@angular/forms';

@Directive({
  selector: '[appAlphabetText]',
  providers: [{provide: NG_VALIDATORS, useExisting: AlphabetTextValidatorDirective, multi: true}]
})
export class AlphabetTextValidatorDirective implements Validator {
    @Input('appAlphabetText') appAlphabetText: string;

  validate(control: AbstractControl): {[key: string]: any} | null {
    return this.appAlphabetText ? alphabetTextValidator(new RegExp(this.appAlphabetText, 'i'))(control)
    : null;
  }
}

export function alphabetTextValidator(nameRe: RegExp): ValidatorFn {

  return (control: AbstractControl): {[key: string]: any} | null => {
    const forbidden = nameRe.test(control.value);
    return forbidden ? {'appAlphabetText': {value: control.value}} : null;
  };
}
