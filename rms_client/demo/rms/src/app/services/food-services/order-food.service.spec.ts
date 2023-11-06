import { TestBed } from '@angular/core/testing';

import { OrderFoodService } from './order-food.service';

describe('OrderFoodService', () => {
  let service: OrderFoodService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OrderFoodService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
