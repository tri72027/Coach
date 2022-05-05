import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { faUser } from '@fortawesome/free-regular-svg-icons';
import { DataShareService } from '../Services/DataShare/data-share.service';
import { SessionStorageService } from '../Services/StorageService/session-storage.service';
import decode from 'jwt-decode';
import { ConnectApiService } from '../Services/Web/connect-api.service';
import { CookieStorageService } from '../Services/StorageService/cookie-storage.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  faUser = faUser;
  userName?: string;
  isLoggedIn: boolean;
  showAdminBoard: boolean;
  private roles?: string;
  constructor(private dataShare: DataShareService, private connectApi: ConnectApiService,
    private sessionStore: SessionStorageService, private route: Router,
    private cookieStore:CookieStorageService) {

    console.log("abc")
    this.dataShare.dataUser.subscribe(data => {
      console.log(data)
      this.checkInit();

    })
  }

  active = 0;
  title = 'coach';
  activeNavigation: any = 1;
  ngOnInit(): void {
    this.checkInit();
  }

  public onRouterOutletActivate(event: any) {
    console.log(event);
  }

  checkInit() {
    this.isLoggedIn = !!this.sessionStore.getToken();
    this.cookieStore.getCookie("auth-token")
    console.log( this.cookieStore.getCookie("auth-token"))
    const token = this.sessionStore.getToken();
    if (token) {
      const payload = decode(token)
      if (this.isLoggedIn) {
        const user = this.sessionStore.getUser();
        this.roles = payload['role'];
        if (this.roles) {
          this.showAdminBoard = (this.roles == 'ROLE_ADMIN');
        }
        this.userName = user['userName'];
      }
    }
  }
  signout() {

    this.sessionStore.signOut();
    this.dataShare.setToken("")
    this.dataShare.setDataUser(null);
    window.location.reload();
  }
}
