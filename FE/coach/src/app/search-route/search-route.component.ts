import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { Province } from '../entity/province';
import { Route } from '../entity/route';
import { ConnectApiService } from '../Services/Web/connect-api.service';
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { NgSelectComponent } from '@ng-select/ng-select';
import { faCalendarAlt } from '@fortawesome/free-solid-svg-icons';
import { Router } from '@angular/router';
import { DataShareService } from '../Services/DataShare/data-share.service';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MaintenanceSystemComponent } from '../Modal/MaintenanceSystem/maintenance-system/maintenance-system.component';



@Component({
  selector: 'app-search-route',
  templateUrl: './search-route.component.html',
  styleUrls: ['./search-route.component.scss']
})

export class SearchRouteComponent implements OnInit {
  faCalendar = faCalendarAlt;
  @ViewChild('NgSelectComponent') ngSelectComponent: NgSelectComponent;
  @Output() activeNav = new EventEmitter();


  formSearchRoute: FormGroup;
  model: NgbDateStruct;
  isDisable: boolean = false;

  constructor(private connectApi: ConnectApiService, private fb: FormBuilder, public _route: Router, private dataShare: DataShareService, private modalService: NgbModal) {
    this.formSearchRoute = fb.group(
      {
        provinceStart: this.fb.control(null, [Validators.required]),
        provinceEnd: this.fb.control(null, [Validators.required]),
        dateOneWay: this.fb.control(null, [Validators.required]),
        dateRoundWay: this.fb.control(null),
        // dateRoundWay: this.fb.control({ value: null, disabled: true }),
        way: this.fb.control('oneWay', [Validators.required]),


      }, { validator: this.dateLessThan('dateOneWay', 'dateRoundWay') }
    );
  }

  valueProvinceStart: number;
  valueProvinceEnd: number;
  public provinceStarts: Province[];
  provinceEnds: Province[];
  route: Route[];
  routeOneWayTemp: Route[];
  routeRoundWayTemp: Route[];
  provinceEndsTemps: Province[] = [];
  routeOneWayID: number;
  routeRoundWayID: number;
  tripRequest: Object
  isDisableRoundWay: boolean = true;
  isDateGreater: boolean


  ngOnInit(): void {

    this.connectApi.get('province/getall').subscribe((response) => {

      this.provinceStarts = response['content'];


      this.provinceEnds = response['content'];
    }, err => {
      this.modalService.open(MaintenanceSystemComponent)
    });
    this.connectApi.get('route/getall').subscribe((response) => {
      this.route = response['content'];
    });
    this.disableDateRoundWay()
  }
  changeWay() {
    this.disableDateRoundWay()
  }

  disableDateRoundWay() {
    if (this.formSearchRoute.value.way === 'oneWay') {
      this.formSearchRoute.controls['dateRoundWay'].disable();
    } else {
      this.formSearchRoute.controls['dateRoundWay'].enable();
    }
  }
  changeProvinceStart(event) {
    this.isDisableRoundWay = false;
    // this.ngSelectComponent.handleClearClick();
    this.routeOneWayTemp = this.route.filter((item) => item.provinceStart === event);
    this.routeRoundWayTemp = this.route.filter((item) => item.provinceEnd === event);

    this.provinceEndsTemps = [];
    this.formSearchRoute.value.provinceEnd = null;
    this.ngSelectComponent.clearModel();

    this.provinceEnds.filter((item) => this.routeOneWayTemp.forEach(itemRT => {
      if (item.provinceID === itemRT.provinceEnd) {
        this.provinceEndsTemps.push(item);
      }
    }));
  }

  changeProvinceEnd() {
    this.routeOneWayTemp.filter((item) => {
      if (this.formSearchRoute.value.provinceStart === item.provinceStart && this.formSearchRoute.value.provinceEnd === item.provinceEnd) {
        this.routeOneWayID = item.routeID;
      }
    });

    this.routeRoundWayTemp.filter((item) => {
      if (this.formSearchRoute.value.provinceEnd === item.provinceStart && this.formSearchRoute.value.provinceStart === item.provinceEnd) {
        this.routeRoundWayID = item.routeID;
      }

    });

  }

  public searRoute() {
    //this.noActiveHomea()
    this.dataShare.senddata();
    if (this.formSearchRoute.value.way === 'oneWay') {
      this.tripRequest = {
        routeOneWayID: this.routeOneWayID,
        dateOneWay: this.formSearchRoute.value.dateOneWay,
      };
    }
    else {
      this.tripRequest = {
        routeOneWayID: this.routeOneWayID,
        dateOneWay: this.formSearchRoute.value.dateOneWay,
        routeRoundWayID: this.routeRoundWayID,
        dateRoundWay: this.formSearchRoute.value.dateRoundWay
      };
    }

    this._route.navigate(['/trip', this.tripRequest]);

  }

  noActiveHomea() {
    this.activeNav.emit(0)
  }
  choosePopularRoute(idx) {

    if (idx === 1) {
      this.autoChooseRoute('TTH', 'ĐN')
    }
    else if (idx === 2) {
      this.autoChooseRoute('ĐN', 'TTH')

    } else if (idx === 3) {
      this.autoChooseRoute('TTH', 'HN')
    }
    else {
      this.autoChooseRoute('HN', 'TTH')
    }
  }

  autoChooseRoute(codeProvinceStart: string, codeProvinceEnd: string) {
    this.provinceStarts.filter(data => {

      if (data.code == codeProvinceStart) {
        this.changeProvinceStart(data.provinceID)
        this.formSearchRoute.value.provinceStart = data.provinceID
        this.formSearchRoute.patchValue(
          {
            provinceStart: data.provinceID
          }
        )
      }
      setTimeout(() => {
        if (data.code == codeProvinceEnd) {
          this.formSearchRoute.value.provinceEnd = data.provinceID
          this.formSearchRoute.patchValue(
            {
              provinceEnd: data.provinceID
            }
          )

          this.changeProvinceEnd()
        }
      }, 200);

    });
  }
  dateLessThan(from: string, to: string) {
    return (group: FormGroup): { [key: string]: any } => {
      let f = group.controls[from];
      let t = group.controls[to];
      const df = new Date(f.value);
      const dt = new Date(t.value);

      if (df.getTime() > dt.getTime() && f.value !== null && t.value !== null) {

        return {
          dates: true
        };
      }
      return {};
    };
  }
}
