import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegularGuestsComponent } from './regular-guests.component';

describe('RegularGuestsComponent', () => {
  let component: RegularGuestsComponent;
  let fixture: ComponentFixture<RegularGuestsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RegularGuestsComponent]
    });
    fixture = TestBed.createComponent(RegularGuestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
