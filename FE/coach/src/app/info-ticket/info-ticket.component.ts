import { Component, OnDestroy, OnInit } from '@angular/core';
import { DataShareService } from '../Services/DataShare/data-share.service';
import { faArrowRight } from '@fortawesome/free-solid-svg-icons';
import { ConnectApiService } from '../Services/Web/connect-api.service';
import { CountDownService } from '../Services/CountDown/count-down.service';
@Component({
  selector: 'app-info-ticket',
  templateUrl: './info-ticket.component.html',
  styleUrls: ['./info-ticket.component.scss']
})
export class InfoTicketComponent implements OnInit, OnDestroy {
  faArrowRight = faArrowRight;
  dataTicketOneWay: any;
  dataTicketRoundWay: any;
  dataCustomer: any;
  totalPrice: number;
  seatChooseDisableOneWay: any;
  seatChooseDisableRoundWay: any;
  counter: any;
  constructor(private DataShare: DataShareService, private connectApi: ConnectApiService, private CountDown: CountDownService) {
    this.DataShare.currentDataCustomerBS.subscribe(data => this.dataCustomer = data);
    this.DataShare.currentDataTicketOneWayBS.subscribe(data => this.dataTicketOneWay = data);
    this.DataShare.currentDataTicketRoundWayBS.subscribe(data => this.dataTicketRoundWay = data);
    this.DataShare.currentSeatChooseDisabledOneWay.subscribe(data => this.seatChooseDisableOneWay = data);
    this.DataShare.currentSeatChooseDisabledRoundWay.subscribe(data => this.seatChooseDisableRoundWay = data);




  }
  ngOnDestroy(): void {
    this.CountDown.setNumber(null);
  }

  ngOnInit(): void {
    this.totalPrice = this.dataTicketOneWay['price'] + this.dataTicketRoundWay['price'];
    this.CountDown.number.subscribe((value) => {

      this.counter = value;
    }
    );
  }
  goBack() {
    this.CountDown.setNumber(null);
    window.history.back();
  }
  goPaid() {
    console.log(this.dataCustomer)

    const requestTicketOneWay =
    {
      ticketCode: this.dataTicketOneWay['codeTicket'],
      amountSeats: this.dataTicketOneWay['amout'],
      dateBuyTicket: new Date(),
      seat: this.dataTicketOneWay['seats'].join(','),
      price: this.dataTicketOneWay['price'],
      tripID: this.dataTicketOneWay['tripID'],
      info : {
        fullName : this.dataCustomer['fullName'],
        phone:this.dataCustomer['phone'],

      },
      statusID: 2
    };
    this.connectApi.post('ticket/save', requestTicketOneWay).subscribe(data => {
      this.connectApi.post('chooseseats/delete', this.seatChooseDisableOneWay).subscribe(dataSeatChooseDisableOneWay => {
        console.log(dataSeatChooseDisableOneWay)
      });
    });

    if (this.dataTicketRoundWay['codeTicket']) {
      const requestTicketRoundWay =
      {
        ticketCode: this.dataTicketRoundWay['codeTicket'],
        amountSeats: this.dataTicketRoundWay['amout'],
        dateBuyTicket: new Date(),
        seat: this.dataTicketRoundWay['seats'].join(','),
        price: this.dataTicketRoundWay['price'],
        tripID: this.dataTicketRoundWay['tripID'],
        info : {
          fullName : this.dataCustomer['fullName'],
          phone:this.dataCustomer['phone'],

        },
        statusID: 2
      };
      this.connectApi.post('ticket/save', requestTicketRoundWay).subscribe(data => {
        console.log(data)

        if (this.seatChooseDisableRoundWay === {}) {
          this.connectApi.post('chooseseats/delete', this.seatChooseDisableRoundWay).subscribe(dataseatChooseDisableRoundWay => {

          });
        }
      });
    }

  }
}
