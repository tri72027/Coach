import { Component, OnInit } from '@angular/core';
import { NgbActiveModal  } from '@ng-bootstrap/ng-bootstrap';
@Component({
  selector: 'app-maintenance-system',
  templateUrl: './maintenance-system.component.html',
  styleUrls: ['./maintenance-system.component.scss']
})
export class MaintenanceSystemComponent implements OnInit {

  constructor(public activeModal: NgbActiveModal) {

   }

  ngOnInit(): void {
  }

}
