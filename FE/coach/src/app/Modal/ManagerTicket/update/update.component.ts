import { Component, Input, OnInit, Output, ViewChild, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NgSelectComponent } from '@ng-select/ng-select';
import { Province } from 'src/app/entity/province';
import { Route } from 'src/app/entity/route';
import { faCalendarAlt } from '@fortawesome/free-solid-svg-icons';
import { ConnectApiService } from 'src/app/Services/Web/connect-api.service';
import { MaintenanceSystemComponent } from '../../MaintenanceSystem/maintenance-system/maintenance-system.component';
import { Trip } from 'src/app/entity/trip';
import { FormatDate } from 'src/app/common/formatdate';



@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.scss']
})
export class UpdateComponent implements OnInit, OnChanges {
  @Input() ticket;

  @Output() dataTicket = new EventEmitter();
  dataTicketSubmit;

  @ViewChild('NgSelectComponent') ngSelectComponent: NgSelectComponent;
  formUpdate: FormGroup;
  faCalendar = faCalendarAlt
  public provinceStarts: Province[];
  public provinceEnds: Province[];
  provinceEndsTemps: Province[] = [];
  routeOneWayID: number;
  route: Route[];
  routeOneWayTemp: Route[];
  dataTripOneWay: Trip[];
  carValueOneWay: number;
  seatChooseDisabledOneWay: string[] = [];
  chooseSeatsCurent: string[] = [];
  statusTicket = []
  constructor(private fb: FormBuilder, public modal: NgbActiveModal, private connectApi: ConnectApiService, private modalService: NgbModal, private FormatDate: FormatDate) {

    this.formUpdate = fb.group(
      {
        provinceStart: [null],
        provinceEnd: [null],
        dateGo: [null],
        statusID: [null]
      }
    )
  }
  ngOnChanges(changes: SimpleChanges) {
    if ('ticket' in changes) {
      this.ticket = changes['ticket'].currentValue;
      console.log(this.ticket)

    }
  }
  ngOnInit(): void {
    console.log(this.ticket)

    this.chooseSeatsCurent =this.ticket[0]['seats'].split(",")
    console.log("🚀 ~ file: update.component.ts ~ line 53 ~ UpdateComponent ~ this.chooseSeatsCurent", this.chooseSeatsCurent)
    this.connectApi.get('route/getall').subscribe((response) => {
      this.route = response['content'];

      const requestTrip = {
        tripID: this.ticket[0]['tripID']
      }
      this.connectApi.post('trip/getbyid', requestTrip).subscribe((responseTrip) => {
        const requestGetTrip =
        {
          routeID: responseTrip['content']['routeID'],
          date: responseTrip['content']['date']
        }
        this.connectApi.post('trip/gettrip', requestGetTrip).subscribe((responseGetTrip) => {
          if (responseGetTrip['content']['data']) {
            this.dataTripOneWay = responseGetTrip['content']['data'];
            if (this.dataTripOneWay) {
              this.changeProvinceStart(responseGetTrip['content']['data'][0]['provinceStartID'])
              this.carValueOneWay = responseTrip['content'].carID;
              console.log( this.dataTripOneWay[0].carID )

              this.formUpdate.patchValue(
                {
                  provinceStart: responseGetTrip['content']['data'][0]['provinceStartID'],
                  dateGo: responseGetTrip['content']['data'][0]['date'],
                  statusID: this.ticket[0]['statusID']
                }
              )
              setTimeout(() => {

                this.changeProvinceEnd()
                this.formUpdate.patchValue(
                  {
                    provinceEnd: responseGetTrip['content']['data'][0]['provinceEndID'],
                  }
                )
              }, 200);
            }
          }

        });

      });

    });
    this.connectApi.get('province/getall').subscribe((responseProvince) => {

      this.provinceStarts = responseProvince['content'];
      this.provinceEnds = responseProvince['content'];
    });
    this.connectApi.get('statusticket/getall').subscribe((response) => {

      this.statusTicket = response['content'];

    });


  }

  changeProvinceStart(event) {
    // this.ngSelectComponent.handleClearClick();

    this.routeOneWayTemp = this.route.filter((item) => item.provinceStart === event);
    this.provinceEndsTemps = []
    this.formUpdate.value.provinceEnd = null;
    this.ngSelectComponent.clearModel();
    if (this.provinceEnds) {
      this.provinceEnds.filter((item) => this.routeOneWayTemp.forEach(itemRT => {
        if (item.provinceID === itemRT.provinceEnd) {
          this.provinceEndsTemps.push(item);
        }
      }))
    }
  }

  changeProvinceEnd() {
    if (this.routeOneWayTemp)
      this.routeOneWayTemp.filter((item) => {
        if (this.formUpdate.value.provinceStart === item.provinceStart && this.formUpdate.value.provinceEnd === item.provinceEnd) {
          this.routeOneWayID = item.routeID;
        }
      });

  }
  public changeCar(idCar) {
    this.seatChooseDisabledOneWay = []
    this.connectApi.get('chooseseats/getall').subscribe((response) => {
      this.dataTripOneWay.filter(data => {
        if (response['content']) {
          response['content'].forEach(idx => {
            if (idx.carID === idCar && data.tripID === idx.tripID) {
              const seats = idx.seats.split(',');
              seats.forEach(element => {
                this.seatChooseDisabledOneWay.push(element);
              });
            }
          });
        }
      });

    });

  }
  getTrip() {
    this.dataTripOneWay = null
    this.seatChooseDisabledOneWay = []
    this.changeProvinceEnd()
    console.log(this.routeOneWayID)
    const requestTrip =
    {
      routeID: this.routeOneWayID,
      date: this.FormatDate.format(this.formUpdate.value.dateGo)
    }

    this.connectApi.post('trip/gettrip', requestTrip).subscribe((response) => {
      if (response['content']['data'].length > 0) {

        this.dataTripOneWay = response['content']['data'];
        if (this.dataTripOneWay) {


          this.carValueOneWay = this.dataTripOneWay[0].carID;
          this.changeCar(this.carValueOneWay)
        }
      }
      else {
        this.dataTripOneWay = null
      }
    });
  }
  submitDataOneWay(data) {

    this.dataTicketSubmit = data
    console.log(data)
  }
  submit() {
    const dataSubmit =
{
  ...this.dataTicketSubmit,
  ticketID: this.ticket[0]['ticketID'],
  statusID: this.formUpdate.value.statusID
}
    console.log( dataSubmit)
    this.dataTicket.emit(dataSubmit)
  }
}
