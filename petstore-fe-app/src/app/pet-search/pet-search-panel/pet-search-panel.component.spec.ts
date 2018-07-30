import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PetSearchPanelComponent } from './pet-search-panel.component';

describe('PetSearchPanelComponent', () => {
  let component: PetSearchPanelComponent;
  let fixture: ComponentFixture<PetSearchPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PetSearchPanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PetSearchPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
