import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PetAdComponent } from './pet-ad.component';

describe('PetAdComponent', () => {
  let component: PetAdComponent;
  let fixture: ComponentFixture<PetAdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PetAdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PetAdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
