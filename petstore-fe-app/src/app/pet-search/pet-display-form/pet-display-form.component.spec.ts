import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PetDisplayFormComponent } from './pet-display-form.component';

describe('PetDisplayFormComponent', () => {
  let component: PetDisplayFormComponent;
  let fixture: ComponentFixture<PetDisplayFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PetDisplayFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PetDisplayFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
