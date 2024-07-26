import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TranslationSwitcherComponent } from './translation-switcher.component';

describe('TranslationSwitcherComponent', () => {
  let component: TranslationSwitcherComponent;
  let fixture: ComponentFixture<TranslationSwitcherComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TranslationSwitcherComponent]
    });
    fixture = TestBed.createComponent(TranslationSwitcherComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
