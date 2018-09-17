import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { PageNotFoundComponent } from './page-not-found.component';

describe('PageNotFoundComponent', () => {
    let comp: PageNotFoundComponent;
    let fixture: ComponentFixture<PageNotFoundComponent>;

    beforeEach(() => {
        TestBed.configureTestingModule({
            declarations: [ PageNotFoundComponent ],
            schemas: [ NO_ERRORS_SCHEMA ]
        });
        fixture = TestBed.createComponent(PageNotFoundComponent);
        comp = fixture.componentInstance;
    });

    it('can load instance', () => {
        expect(comp).toBeTruthy();
    });

    it('has error string as Invalid Location', () => {
      comp.ngOnInit();
      expect(comp.errorString).toMatch('Invalid Location');
    });

});
