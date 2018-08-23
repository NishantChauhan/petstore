import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PetBannerComponent } from './pet-banner.component';

describe('PetBannerComponent', () => {
  let component: PetBannerComponent;
  let fixture: ComponentFixture<PetBannerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PetBannerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PetBannerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
