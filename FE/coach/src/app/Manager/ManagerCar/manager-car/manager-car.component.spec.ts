import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagerCarComponent } from './manager-car.component';

describe('ManagerCarComponent', () => {
  let component: ManagerCarComponent;
  let fixture: ComponentFixture<ManagerCarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManagerCarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagerCarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
