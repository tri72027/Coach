import { Injectable } from '@angular/core';
import { timer, Subject} from "rxjs";
@Injectable({
  providedIn: 'root'
})
export class CountDownService {
  public number = new Subject();
  getCounter(tick) {
    return timer(0, tick);
  }
  setNumber (num:number)
  {
    this.number.next(num);
  }
  constructor() { }
}
