import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BestSellerChartComponent } from './best-seller-chart.component';

describe('BestSellerChartComponent', () => {
  let component: BestSellerChartComponent;
  let fixture: ComponentFixture<BestSellerChartComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BestSellerChartComponent]
    });
    fixture = TestBed.createComponent(BestSellerChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
