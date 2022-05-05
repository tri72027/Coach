import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagerTicketComponent } from './manager-ticket.component';

describe('ManagerTicketComponent', () => {
  let component: ManagerTicketComponent;
  let fixture: ComponentFixture<ManagerTicketComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManagerTicketComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagerTicketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
