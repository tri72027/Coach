import { Injectable } from "@angular/core";


@Injectable({
  providedIn: 'root'
})
export class FormatDate {

  format(date: string): string {
    if (date === null) {
      return null
    } else {
      const datesplit = date.split("-")
      const month = parseInt(datesplit[1], 10) > 9 ? datesplit[1] : '0' + datesplit[1];
      const day = parseInt(datesplit[2], 10) > 9 ? datesplit[2] : '0' + datesplit[2];
      const dateOneWay = [datesplit[0], month, day].join('-');
      return dateOneWay;
    }
  }
}
