import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MaintenanceSystemComponent } from 'src/app/Modal/MaintenanceSystem/maintenance-system/maintenance-system.component';
import { ConnectApiService } from 'src/app/Services/Web/connect-api.service';

@Component({
  selector: 'app-addnew-and-update-car',
  templateUrl: './addnew-and-update-car.component.html',
  styleUrls: ['./addnew-and-update-car.component.scss']
})
export class AddnewAndUpdateCarComponent implements OnInit {
  title;
  formUpdateCar: FormGroup
  submitted: boolean
  statuss
  carID;
  constructor(private fb: FormBuilder, private connectApi: ConnectApiService, private modalService: NgbModal, public modal: NgbActiveModal) {
    this.formUpdateCar = this.fb.group(
      {
        code: [null],
        name: [null],
        licensePlates: [null],
        amount: [null],
        price: [null],
        statusID: [null]
      }
    )

  }

  ngOnInit(): void {
    this.connectApi.get("statuscar/getall").subscribe(response => {
      this.statuss = response['content'];
    }, err => {
      this.modalService.open(MaintenanceSystemComponent)
    });
    if(this.carID)
    {
      const request=
      {
        carID: this.carID
      }
      this.connectApi.post("car/getbyid",request).subscribe(response => {
        this.formUpdateCar.setValue(
          {
            code: response['content']['code'],
            name: response['content']['name'],
            licensePlates: response['content']['licensePlates'],
            amount: response['content']['amount'],
            price: response['content']['price'],
            statusID: response['content']['statusID']
          }
        )
      }, err => {
        this.modalService.open(MaintenanceSystemComponent)
      });
    }
  }
  save() {
    this.submitted = true;
    if (this.submitted && this.formUpdateCar.valid)
      this.modal.close(this.formUpdateCar.value)
  }
}
