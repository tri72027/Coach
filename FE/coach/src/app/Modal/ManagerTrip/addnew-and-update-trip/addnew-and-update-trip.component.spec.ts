import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddnewAndUpdateTripComponent } from './addnew-and-update-trip.component';

describe('AddnewAndUpdateTripComponent', () => {
  let component: AddnewAndUpdateTripComponent;
  let fixture: ComponentFixture<AddnewAndUpdateTripComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddnewAndUpdateTripComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddnewAndUpdateTripComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
