import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DataShareService } from '../Services/DataShare/data-share.service';
import { CookieStorageService } from '../Services/StorageService/cookie-storage.service';
import { SessionStorageService } from '../Services/StorageService/session-storage.service';
import { ConnectApiService } from '../Services/Web/connect-api.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  formLogin: FormGroup
  submitted: boolean;
  isLoggedIn: boolean;
  isLoginFailed: boolean;
  errorMessage = '';
  roles: string[] = [];
  countdownSecond = 5;
  interval;
  constructor(private connectApi: ConnectApiService, private fb: FormBuilder,
    private sessionStore: SessionStorageService, private route: Router,
    private dataShare: DataShareService, private cookieService : CookieStorageService) {
    this.formLogin = this.fb.group(
      {
        userName: [null, [Validators.required, Validators.minLength(4), Validators.maxLength(20)]],
        password: [null, [Validators.required, Validators.minLength(6), Validators.maxLength(20)]]

      }
    )


  }


  ngOnInit(): void {
    if (this.sessionStore.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.sessionStore.getUser().roles;
    }
  }

  login() {
    this.submitted = true;
    if (this.formLogin.valid) {
      const requestRegister =
      {
        ...this.formLogin.value

      }
      console.log("abc")
      this.connectApi.post('user/signin', requestRegister).subscribe(
        data => {
          console.log(data);
          this.sessionStore.saveToken(data['accessToken'])

          this.dataShare.setToken(data['accessToken'])
          this.cookieService.setCookie("auth-token",data['accessToken'])
          this.sessionStore.saveUser({ id: data['id'], userName: data['username'] })
          this.isLoggedIn = true;
          this.isLoginFailed = false;


          this.interval = setInterval(
            () => {
              this.countdownSecond--;


            }
            , 1000
          )
          setTimeout(() => {

            if (this.interval) {
              clearInterval(this.interval);
            }
            this.route.navigate(['']);
            this.dataShare.setDataUser({ id: data['id'], userName: data['username'] })

          }, 5000);

        },
        err => {
          console.log(err)
          this.errorMessage = err.message;
          this.isLoginFailed = true;
        }
      );
    }
  }
}
