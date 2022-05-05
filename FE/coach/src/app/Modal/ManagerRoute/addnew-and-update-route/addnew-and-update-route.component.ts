import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Province } from 'src/app/entity/province';
import { ConnectApiService } from 'src/app/Services/Web/connect-api.service';
import { MaintenanceSystemComponent } from '../../MaintenanceSystem/maintenance-system/maintenance-system.component';

@Component({
  selector: 'app-addnew-and-update-route',
  templateUrl: './addnew-and-update-route.component.html',
  styleUrls: ['./addnew-and-update-route.component.scss']
})
export class AddnewAndUpdateRouteComponent implements OnInit {
  @Input() title;
  formUpdateRoute: FormGroup;
  provinces: Province[];
  statuss;
  routeID;
  data;
  isUpdate: boolean
  submitted: boolean
  constructor(private fb: FormBuilder, public modal: NgbActiveModal, private connectApi: ConnectApiService, private modalService: NgbModal) {



    this.formUpdateRoute = this.fb.group(
      {
        departureTime: [null, Validators.required],
        journeyTime: [null, Validators.required],
        provinceStart: [null,Validators.required],
        provinceEnd: [null,Validators.required],
        price: [null,Validators.required],
        statusID: [null,Validators.required]
      }
    )
  }

  ngOnInit(): void {
    this.connectApi.get("province/getall").subscribe(response => {
      this.provinces = response['content'];
    }, err => {
      this.modalService.open(MaintenanceSystemComponent)
    });
    this.connectApi.get("status/getall").subscribe(response => {
      this.statuss = response['content'];
    }, err => {
      this.modalService.open(MaintenanceSystemComponent)
    });
    if (this.isUpdate) {
      const request = {
        routeID: this.routeID
      }
      this.connectApi.post("route/getbyid", request).subscribe(response => {
        this.data = response['content'];
        console.log(this.data)
        this.formUpdateRoute.setValue(
          {
            departureTime: this.data['departureTime'],
            journeyTime: this.data['journeyTime'],
            provinceStart: this.data['provinceStart'],
            provinceEnd: this.data['provinceEnd'],
            price: this.data['price'],
            statusID: this.data['statusID']
          }
        )
      });
    }
  }
  save() {
    this.submitted = true;
    if(this.submitted && this.formUpdateRoute.valid)
    this.modal.close(this.formUpdateRoute.value)
  }

}
