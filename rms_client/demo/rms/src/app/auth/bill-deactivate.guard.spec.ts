import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { billDeactivateGuard } from './bill-deactivate.guard';

describe('billDeactivateGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => billDeactivateGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
