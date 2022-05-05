import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormatDate } from 'src/app/common/formatdate';
import { ConnectApiService } from 'src/app/Services/Web/connect-api.service';
import { MaintenanceSystemComponent } from '../../MaintenanceSystem/maintenance-system/maintenance-system.component';
import { faCalendarAlt } from '@fortawesome/free-solid-svg-icons';
@Component({
  selector: 'app-addnew-and-update-trip',
  templateUrl: './addnew-and-update-trip.component.html',
  styleUrls: ['./addnew-and-update-trip.component.scss']
})
export class AddnewAndUpdateTripComponent implements OnInit {
  @Input() title;
  faCalendar = faCalendarAlt;
  tripID: number;
  formUpdateTrip: FormGroup
  submitted: boolean
  routes: [];
  cars: [];
  statuss: [];
  isUpdate: boolean


  constructor(private fb: FormBuilder, private connectApi: ConnectApiService, public modal: NgbActiveModal,
    private FormatDate: FormatDate, private modalService: NgbModal) {

    this.formUpdateTrip = this.fb.group(
      {
        date: [null, Validators.required],
        routeID: [null, Validators.required],
        carID: [null, Validators.required],
        statusID: [null, Validators.required]
      }
    )

  }

  ngOnInit(): void {
    this.connectApi.get("route/searchdropdownroute").subscribe(response => {
      this.routes = response['content'];

    }, err => {
      this.modalService.open(MaintenanceSystemComponent)
    });
    this.connectApi.get("statustrip/getall").subscribe(response => {
      this.statuss = response['content'];
    }, err => {
      this.modalService.open(MaintenanceSystemComponent)
    });
    this.connectApi.get("car/getall").subscribe(response => {
      this.cars = response['content'];
    }, err => {
      this.modalService.open(MaintenanceSystemComponent)
    });
    const request =
    {
      tripID: this.tripID
    }
    if (this.isUpdate) {
      this.connectApi.post("trip/getbyid", request).subscribe(response => {

        this.formUpdateTrip.setValue(
          {
            date: response['content']['date'],
            routeID: response['content']['routeID'],
            carID: response['content']['carID'],
            statusID: response['content']['statusID']
          }
        )


      })
    }
  }

  save() {
    this.submitted = true;
    if (this.submitted && this.formUpdateTrip.valid) {
      this.modal.close(this.formUpdateTrip.value)
    }
  }

}
