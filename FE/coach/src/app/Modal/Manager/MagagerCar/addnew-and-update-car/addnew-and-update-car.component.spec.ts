import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddnewAndUpdateCarComponent } from './addnew-and-update-car.component';

describe('AddnewAndUpdateCarComponent', () => {
  let component: AddnewAndUpdateCarComponent;
  let fixture: ComponentFixture<AddnewAndUpdateCarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddnewAndUpdateCarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddnewAndUpdateCarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
