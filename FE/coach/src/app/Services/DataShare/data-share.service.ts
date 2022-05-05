import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject } from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class DataShareService {
  private dataTicketOneWayBS = new BehaviorSubject({});
  private dataTicketRoundWayBS = new BehaviorSubject({});
  private dataCustomerBS = new BehaviorSubject({});
  private seatChooseDisabledOneWay = new BehaviorSubject({});
  private seatChooseDisabledRoundWay = new BehaviorSubject({});
  public dataNav = new Subject();
  public dataUser = new Subject();
  private token;

  currentDataTicketOneWayBS = this.dataTicketOneWayBS.asObservable();
  currentDataTicketRoundWayBS = this.dataTicketRoundWayBS.asObservable();
  currentDataCustomerBS = this.dataCustomerBS.asObservable();
  currentSeatChooseDisabledOneWay = this.seatChooseDisabledOneWay.asObservable();
  currentSeatChooseDisabledRoundWay = this.seatChooseDisabledRoundWay.asObservable();

  getToken():string  {
    return this.token;
  }
  setToken(token:string):void{
    this.token = token;
  }
  changeDataTicketOneWayBS(data: any) {
    this.dataTicketOneWayBS.next(data);
  }
  changeDataTicketRoundWayBS(data: any) {
    this.dataTicketRoundWayBS.next(data);
  }

  changeDataCustomerBS(data: any) {
    this.dataCustomerBS.next(data);
  }
  changeSeatChooseDisabledOneWay(data: any) {
    this.seatChooseDisabledOneWay.next(data);
  }
  changeSeatChooseDisabledRoundWay(data: any) {
    this.seatChooseDisabledRoundWay.next(data);
  }

  senddata() {
    this.dataNav.next(0);
  }
  setDataUser(data: any) {
    this.dataUser.next(data);
  }
  constructor() { }
}
