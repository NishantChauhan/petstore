import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayPetComponent } from './display-pet.component';

describe('DisplayPetComponent', () => {
  let component: DisplayPetComponent;
  let fixture: ComponentFixture<DisplayPetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayPetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayPetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
