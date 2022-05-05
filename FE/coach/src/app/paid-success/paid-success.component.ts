import { Component, OnInit } from '@angular/core';
import { DataShareService } from '../Services/DataShare/data-share.service';
import { faArrowRight } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-paid-success',
  templateUrl: './paid-success.component.html',
  styleUrls: ['./paid-success.component.scss']
})
export class PaidSuccessComponent implements OnInit {
  faArrowRight = faArrowRight;
  dataTicketOneWay: any;
  dataTicketRoundWay: any;
  dataCustomer: any;
  constructor(private DataShare: DataShareService) {
  }

  ngOnInit(): void {
    this.DataShare.currentDataCustomerBS.subscribe(data => this.dataCustomer = data);
    this.DataShare.currentDataTicketOneWayBS.subscribe(data => this.dataTicketOneWay = data);
    this.DataShare.currentDataTicketRoundWayBS.subscribe(data => this.dataTicketRoundWay = data);
  }
  goBack() {
    window.history.back();
  }
  goPaid() {

  }
}
