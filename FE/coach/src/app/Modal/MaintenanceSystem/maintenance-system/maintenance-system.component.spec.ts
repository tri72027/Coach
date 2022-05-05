import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MaintenanceSystemComponent } from './maintenance-system.component';

describe('MaintenanceSystemComponent', () => {
  let component: MaintenanceSystemComponent;
  let fixture: ComponentFixture<MaintenanceSystemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MaintenanceSystemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MaintenanceSystemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
