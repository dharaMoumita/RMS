import { TestBed } from '@angular/core/testing';

import { TaleService } from './tale.service';

describe('TaleService', () => {
  let service: TaleService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TaleService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
