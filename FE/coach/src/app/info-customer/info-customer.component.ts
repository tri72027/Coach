import { Route } from '@angular/compiler/src/core';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs'
import { CountDownService } from '../Services/CountDown/count-down.service';
import { DataShareService } from '../Services/DataShare/data-share.service';
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  selector: 'app-info-customer',
  templateUrl: './info-customer.component.html',
  styleUrls: ['./info-customer.component.scss']
})
export class InfoCustomerComponent implements OnInit {
  countDown: Subscription;
  counter = 600;
  tick = 1000;
  infoCusomer: FormGroup;
  submitted:boolean;
  constructor(private fb: FormBuilder, private DataShare: DataShareService, private CountDown: CountDownService, private _route :  Router) {

    this.infoCusomer = fb.group({
      fullName: [null, Validators.required],
      phone: [null, [Validators.required, Validators.pattern('^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\s\./0-9]*$')]],
      // email: [null, [Validators.required, Validators.email]],
      // identificationNumber: [null,[ Validators.required, Validators.minLength(9),Validators.maxLength(12)]],
      accept: [null, Validators.required]
    });

  }
  ngOnInit(): void {
    this.countDown = this.CountDown.getCounter(this.tick).subscribe(() => {if (this.counter !== 0) { this.counter--; this.CountDown.setNumber(this.counter) }})
  }

  goBack() {
    window.history.back();
  }
  goInfoTicket() {

    this.submitted = true;
    if(this.infoCusomer.valid)
    {
      this._route.navigate(['/infoticket'])
      this.DataShare.changeDataCustomerBS(this.infoCusomer.value)
    }

  }
}
