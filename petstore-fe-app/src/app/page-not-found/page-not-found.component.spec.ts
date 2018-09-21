import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DebugElement } from '@angular/core';
import { PageNotFoundComponent } from './page-not-found.component';
import { By } from '@angular/platform-browser';


describe('PageNotFoundComponent', () => {
    let comp: PageNotFoundComponent;
    let fixture: ComponentFixture<PageNotFoundComponent>;
    let dbgElmnt: DebugElement;
    let htmlElmnt: HTMLElement;

    beforeEach(() => {
        TestBed.configureTestingModule({
            declarations: [ PageNotFoundComponent ],
        });
        fixture = TestBed.createComponent(PageNotFoundComponent);
        comp = fixture.componentInstance;
        dbgElmnt = fixture.debugElement.query(By.css('strong'));
        htmlElmnt = dbgElmnt.nativeElement;
    });

    it('can load instance', () => {
        expect(comp).toBeTruthy();
    });

    it('has error string as Invalid Location', () => {
      comp.ngOnInit();
      expect(comp.errorString).toMatch('Invalid Location');
    });

    it('should have error message as \'Invalid Location\'', async(() => {
        fixture = TestBed.createComponent(PageNotFoundComponent);
        fixture.detectChanges();
        const compiled = fixture.debugElement.nativeElement;
        expect(compiled.querySelector('strong').textContent).toContain('Invalid Location');
    }));

});
