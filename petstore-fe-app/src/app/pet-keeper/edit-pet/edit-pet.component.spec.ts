import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditPetComponent } from './edit-pet.component';

describe('EditPetComponent', () => {
  let component: EditPetComponent;
  let fixture: ComponentFixture<EditPetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditPetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditPetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
