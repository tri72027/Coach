import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ConnectApiService } from '../Services/Web/connect-api.service';
import { faArrowRight } from '@fortawesome/free-solid-svg-icons';
import { Trip } from '../entity/trip';
import { DataShareService } from '../Services/DataShare/data-share.service';
import { FormatDate } from '../common/formatdate';

@Component({
  selector: 'app-choose-trip',
  templateUrl: './choose-trip.component.html',
  styleUrls: ['./choose-trip.component.scss']
})
export class ChooseTripComponent implements OnInit {
  faChevronRight = faArrowRight;
  dataTripOneWay: Trip[];
  dataTripRoundWay: Trip[];
  requestOneWay: any;
  requestRoundWay: any;
  carValueOneWay: number;
  carValueRoundWay: number;
  seatChooseDisabledOneWay: string[] = [];
  seatChooseDisabledRoundWay: string[] = [];
  seatChooseDisabledSubmit: string[] = [];
  dataTicketOneWay: any;
  dataTicketRoundWay: any;


  // tslint:disable-next-line: max-line-length
  constructor(private Route: ActivatedRoute, private connectApi: ConnectApiService, private ElementAngular: ElementRef,
    private DataShare: DataShareService, private _route: Router, private FormatDate: FormatDate) {
    const dateOneWay = this.FormatDate.format(this.Route.snapshot.paramMap.get('dateOneWay'))
    this.Route.paramMap.subscribe(params => {
      this.requestOneWay = {
        routeID: params.get('routeOneWayID'),
        date: dateOneWay
      };
      if (this.Route.snapshot.paramMap.get('dateRoundWay') !== null) {
        const dateRoundWay = this.FormatDate.format(this.Route.snapshot.paramMap.get('dateRoundWay'))
        this.requestRoundWay =
        {
          routeID: params.get('routeRoundWayID'),
          date: dateRoundWay
        };
      }
    });
  }
  ngOnInit(): void {
    this.connectApi.post('trip/gettrip', this.requestOneWay).subscribe((response) => {
      this.dataTripOneWay = response['content']['data'];
      if (this.dataTripOneWay) {
        this.carValueOneWay = this.dataTripOneWay[0].carID;
      }
    });

    this.connectApi.get('chooseseats/getall').subscribe((response) => {
      if (this.dataTripOneWay) {
        this.dataTripOneWay.filter(data => {
          if (response['content']) {
            response['content'].forEach(idx => {
              if (data.carID === idx.carID && data.tripID === idx.tripID) {
                const seats = idx.seats.split(',')
                seats.forEach(element => {
                  this.seatChooseDisabledOneWay.push(element);
                });
              }
            });
          }

        });
      }
      if (this.Route.snapshot.paramMap.get('dateRoundWay') !== null) {
        this.dataTripRoundWay.filter(data => {
          if (response['content']) {
            response['content'].forEach(idx => {
              if (data.carID === idx.carID && data.tripID === idx.tripID) {
                const seats = idx.seats.split(',')
                seats.forEach(element => {
                  this.seatChooseDisabledRoundWay.push(element)
                });
              }
            });
          }

        })
      }
    });
    if (this.Route.snapshot.paramMap.get('dateRoundWay') !== null) {
      this.connectApi.post('trip/gettrip', this.requestRoundWay).subscribe((response) => {
        this.dataTripRoundWay = response['content']['data'];

        if (this.dataTripRoundWay.length !== 0) {
          this.carValueRoundWay = this.dataTripRoundWay[0].carID;
        }
      });
    }

  }
  public changeCar(idCar) {
    this.seatChooseDisabledOneWay = []
    this.connectApi.get('chooseseats/getall').subscribe((response) => {
      if (response['content']) {
        this.dataTripOneWay.filter(data => {
          response['content'].forEach(idx => {
            if (idx.carID === idCar && data.tripID === idx.tripID) {
              const seats = idx.seats.split(',');
              seats.forEach(element => {
                this.seatChooseDisabledOneWay.push(element);
              });
            }
          });
        });
      }
      if (this.Route.snapshot.paramMap.get('dateRoundWay') !== null) {
        this.dataTripRoundWay.filter(data => {
          response['content'].forEach(idx => {
            if (idx.carID === idCar && data.tripID === idx.tripID) {
              const seats = idx.seats.split(',');
              seats.forEach(element => {
                this.seatChooseDisabledRoundWay.push(element);
              });
            }
          });
        });
      }
    });

  }
  submitDataOneWay(data) {
    this.dataTicketOneWay = data;
    this.seatChooseDisabledSubmit = [];
    this.DataShare.changeDataTicketOneWayBS(data);
    this.seatChooseDisabledSubmit.push(data.seats);
    this.setSeatChooseDisableDataShare(data);
    this.DataShare.changeSeatChooseDisabledOneWay(this.setSeatChooseDisableDataShare(data));

  }
  submitDataRoundWay(data) {
    this.dataTicketRoundWay = data;
    this.DataShare.changeDataTicketRoundWayBS(data);
    this.seatChooseDisabledSubmit.push(data.seats);
    this.DataShare.changeSeatChooseDisabledRoundWay(this.setSeatChooseDisableDataShare(data));
  }

  setSeatChooseDisableDataShare(data: any) {
    return {
      tripID: data['tripID'],
      carID: data['carID'],
      seats: data['seats'].toString()
    };
  }
  goInfo() {
    this.DataShare.currentDataTicketOneWayBS.subscribe(data => this.seatChooseDisabledSubmit = data['seats']);
    const dataRequest =
    {
      tripID: this.dataTicketOneWay['tripID'],
      carID: this.dataTicketOneWay['carID'],
      seats: this.dataTicketOneWay['seats'].toString()
    };

    this.connectApi.post('chooseseats/save', dataRequest).subscribe((response) => {
    });
    if (this.Route.snapshot.paramMap.get('dateRoundWay') !== null) {
      const dataRequestRoundWay =
      {
        tripID: this.dataTicketRoundWay['tripID'],
        carID: this.dataTicketRoundWay['carID'],
        seats: this.dataTicketRoundWay['seats'].toString()
      };
      this.connectApi.post('chooseseats/save', dataRequestRoundWay).subscribe((response) => {
      });
    }

    this._route.navigateByUrl('infocustomer');

  }
  goBack() {
    window.history.back();

  }
}

