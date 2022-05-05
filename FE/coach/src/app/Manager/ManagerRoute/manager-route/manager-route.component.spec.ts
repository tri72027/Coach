import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagerRouteComponent } from './manager-route.component';

describe('ManagerRouteComponent', () => {
  let component: ManagerRouteComponent;
  let fixture: ComponentFixture<ManagerRouteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManagerRouteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagerRouteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
