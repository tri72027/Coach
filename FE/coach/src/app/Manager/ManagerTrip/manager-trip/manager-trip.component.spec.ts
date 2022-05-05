import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagerTripComponent } from './manager-trip.component';

describe('ManagerTripComponent', () => {
  let component: ManagerTripComponent;
  let fixture: ComponentFixture<ManagerTripComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManagerTripComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagerTripComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
