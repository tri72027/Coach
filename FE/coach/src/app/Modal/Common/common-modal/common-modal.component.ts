import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-common-modal',
  templateUrl: './common-modal.component.html',
  styleUrls: ['./common-modal.component.scss']
})
export class CommonModalComponent implements OnInit {
  @Input() content
  @Output() callBack = new EventEmitter()

  constructor(public modal: NgbActiveModal) { }

  ngOnInit(): void {
  }
  submit() {
    this.callBack.emit()
  }
}
