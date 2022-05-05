import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ConnectApiService } from 'src/app/Services/Web/connect-api.service';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.scss']
})
export class DetailComponent implements OnInit {
  @Input() ticket
  data

  constructor(public modal: NgbActiveModal, private connectApi: ConnectApiService) { }

  ngOnInit(): void {

    const request =
    {
      ticketID: this.ticket[0]['ticketID']
    }
    this.connectApi.post('ticket/getticketdetail', request).subscribe(response => {
      this.data = response['content'][0]
      console.log(this.data)
    })
  }

}
