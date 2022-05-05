import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-delete-success',
  templateUrl: './delete-success.component.html',
  styleUrls: ['./delete-success.component.scss']
})
export class DeleteSuccessComponent implements OnInit {

  constructor( public modal :NgbActiveModal) { }

  ngOnInit(): void {
  }

}
