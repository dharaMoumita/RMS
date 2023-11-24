import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReserveTablesComponent } from './reserve-tables.component';

describe('ReserveTablesComponent', () => {
  let component: ReserveTablesComponent;
  let fixture: ComponentFixture<ReserveTablesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ReserveTablesComponent]
    });
    fixture = TestBed.createComponent(ReserveTablesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
