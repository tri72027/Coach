import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ConnectApiService } from '../Services/Web/connect-api.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  submitted: boolean;
  formRegister: FormGroup
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';
  countdownSecond = 5;
  interval;
  constructor(private connectApi: ConnectApiService, private fb: FormBuilder, private router: Router) {
    this.formRegister = this.fb.group(
      {
        userName: [null, [Validators.required, Validators.minLength(4), Validators.maxLength(20)]],
        password: [null, [Validators.required, Validators.minLength(6), Validators.maxLength(20)]]
      }
    )
  }
  ngOnInit(): void {

  }
  register() {
    this.submitted = true;
    if (this.formRegister.valid) {
      const requestRegister =
      {
        ...this.formRegister.value,
        createTime: new Date()
      }
      console.log("abc")
      this.connectApi.post('user/signup', requestRegister).subscribe(
        data => {
          console.log(data);
          this.isSuccessful = true;
          this.isSignUpFailed = false;
         this.interval= setInterval(
            () => {
             this.countdownSecond--;

            }
            , 1000
          )
          setTimeout(() => {
            if (this.interval) {
              clearInterval(this.interval);
            }
            this.router.navigateByUrl("/login")
          }, 5000);

        },
        err => {
          this.errorMessage = err.error.message;
          this.isSignUpFailed = true;
        }
      );
    }
  }
}



