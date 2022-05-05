import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddnewAndUpdateRouteComponent } from './addnew-and-update-route.component';

describe('AddnewAndUpdateRouteComponent', () => {
  let component: AddnewAndUpdateRouteComponent;
  let fixture: ComponentFixture<AddnewAndUpdateRouteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddnewAndUpdateRouteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddnewAndUpdateRouteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
