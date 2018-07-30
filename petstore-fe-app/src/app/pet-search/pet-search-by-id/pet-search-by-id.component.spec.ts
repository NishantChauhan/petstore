import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PetSearchByIdComponent } from './pet-search-by-id.component';

describe('PetSearchByIdComponent', () => {
  let component: PetSearchByIdComponent;
  let fixture: ComponentFixture<PetSearchByIdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PetSearchByIdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PetSearchByIdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
