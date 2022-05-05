import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChooseTripComponent } from './choose-trip.component';

describe('ChooseTripComponent', () => {
  let component: ChooseTripComponent;
  let fixture: ComponentFixture<ChooseTripComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChooseTripComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChooseTripComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
