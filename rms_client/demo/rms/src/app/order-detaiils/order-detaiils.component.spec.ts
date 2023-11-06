import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderDetaiilsComponent } from './order-detaiils.component';

describe('OrderDetaiilsComponent', () => {
  let component: OrderDetaiilsComponent;
  let fixture: ComponentFixture<OrderDetaiilsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OrderDetaiilsComponent]
    });
    fixture = TestBed.createComponent(OrderDetaiilsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
