import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TopEmployeeChartComponent } from './top-employee-chart.component';

describe('TopEmployeeChartComponent', () => {
  let component: TopEmployeeChartComponent;
  let fixture: ComponentFixture<TopEmployeeChartComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TopEmployeeChartComponent]
    });
    fixture = TestBed.createComponent(TopEmployeeChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
