import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PetStoreHomeComponent } from './pet-store-home.component';

describe('PetStoreHomeComponent', () => {
  let component: PetStoreHomeComponent;
  let fixture: ComponentFixture<PetStoreHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PetStoreHomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PetStoreHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
