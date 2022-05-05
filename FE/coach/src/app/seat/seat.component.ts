import { NodeWithI18n } from '@angular/compiler';
import { EventEmitter, Component, Input, OnInit, OnChanges, SimpleChanges, Output } from '@angular/core';
import { Trip } from '../entity/trip';
import { Ticket } from '../entity/ticket';
import { ConnectApiService } from '../Services/Web/connect-api.service';


@Component({
  selector: 'app-seat',
  templateUrl: './seat.component.html',
  styleUrls: ['./seat.component.scss']
})
export class SeatComponent implements OnInit, OnChanges {

  @Input() dataTrip: Trip[];
  @Input() carValueOneWay: number;
  @Input() carValueRoundWay: number;
  @Output() dataSeatCar = new EventEmitter();
  @Input() chooseSeats = [];

  seats: string[] = [];
  dataTripTemp: Trip;

  seatsDisable: string[] = [];
  totalPrice: number = 0;
  dataSeat: Object;
  OneWay: boolean
  chooseStore = new Set();
  @Input() seatChooseDisabled: string[] = [];
  constructor(private connectApi: ConnectApiService) {

  }
  ngOnChanges(changes: SimpleChanges): void {

    if ('dataTrip' in changes) {
      this.dataTrip = changes['dataTrip'].currentValue;
    }
    if ('seatChooseDisabled' in changes) {
      this.seatChooseDisabled = changes['seatChooseDisabled'].currentValue;
    }
    if ('chooseSeats' in changes) {
      this.chooseSeats = changes['chooseSeats'].currentValue;

    }
    if ('carValueOneWay' in changes) {
      this.OneWay = false;
      // this.chooseSeats = [];
      this.totalPrice = 0;
      this.carValueOneWay = changes['carValueOneWay'].currentValue;
      this.changeSeat(this.carValueOneWay);
    }
    if ('carValueRoundWay' in changes) {
      this.OneWay = true;
      // this.chooseSeats = [];
      this.totalPrice = 0;
      this.carValueOneWay = changes['carValueRoundWay'].currentValue;
      this.changeSeat(this.carValueRoundWay);
    }
  }
  public chooseSeat(even, idx) {
    // console.log(even.target.checked)
    if (even.target.checked === true) {
      this.chooseStore.add(idx)
       this.chooseSeats.push(idx)
      console.log(this.chooseSeats)
    } else {
      this.chooseSeats = this.chooseSeats.filter(data => data !== idx)
      this.chooseStore.delete(idx)
      console.log(this.chooseSeats)
    }
    this.totalPrice = (this.dataTripTemp.carPrice + this.dataTripTemp.routePrice) * this.chooseSeats.length;
    this.dataSeat =
    {
      seats: this.chooseSeats,
      price: this.totalPrice,
      amout: this.chooseSeats.length,
      codeTicket: this.dataTripTemp.carCode + this.chooseSeats.join(''),
      provinceStart: this.dataTripTemp.provinceStartName,
      provinceEnd: this.dataTripTemp.provinceEndName,
      licensePlates: this.dataTripTemp.licensePlates,
      departureTime: this.dataTripTemp.departureTime,
      dateTrip: this.dataTripTemp.date,
      tripID: this.dataTripTemp.tripID,
      carID: this.dataTripTemp.carID

    };
    this.dataSeatCar.emit(this.dataSeat);
  }
  ngOnInit(): void {

  }
  public changeSeat(idCar) {
    this.chooseStore.clear()
    this.seats = [];
    console.log(this.seats)
    console.log(this.dataTrip)
    this.dataTrip.filter(data => {
      if (data.carID === idCar) {
        for (let i = 1; i <= data.carAmount; i++) {
          if (i < 10) {
            console.log(i)
            const seat = 'A0' + i;
            this.seats.push(seat);
          } else {
            const seat = 'A' + i;
            this.seats.push(seat);
          }
        }
        this.dataTripTemp = { ...data };

        const request = {
          'tripID': this.dataTripTemp.tripID,
          'statusID': 2
        };
        this.connectApi.post('ticket/getbytripid', request).subscribe((response) => {

          let dataTicket: Ticket[];
          dataTicket = response['content'];
          this.totalPrice = (this.dataTripTemp.carPrice + this.dataTripTemp.routePrice) * this.chooseSeats.length;
          this.seatsDisable = []
          if (dataTicket !== null) {
            dataTicket.filter(dataT => {
              if (dataT['tripID'] === this.dataTripTemp.tripID) {
                if (this.dataTripTemp.carID === idCar) {
                  // console.log(dataT['seat'])
                  // console.log(this.chooseSeats.toString())
                  // console.log(dataT['seat'].length)
                  if (dataT['seat'].length === 3) {
                    if (this.chooseSeats.length > 0) {
                      if (this.chooseSeats.toString().length === 3 && this.chooseSeats.toString() === dataT['seat']) {
                        console.log("abc12")

                      } else {

                        this.seatsDisable.push(dataT['seat']);

                        console.log("abc23")
                      }
                    }
                    else {
                      this.seatsDisable.push(dataT['seat']);

                      console.log("abc41")
                    }
                  }
                  else {
                    console.log(this.chooseSeats.toString())
                    if (this.chooseSeats.length > 0) {
                      console.log(this.chooseSeats.length)
                      console.log(dataT['seat'].length)
                      if (this.chooseSeats.length == 1) {
                        console.log("abc")
                        const seatSplit = dataT['seat'].split(',')
                        seatSplit.forEach((data) => {
                          this.seatsDisable.push(data)

                        });
                      } else {
                        console.log(dataT['seat'])
                        console.log(this.chooseSeats.toString())

                        console.log("a42bc")
                        const seatSplitCurrent = this.chooseSeats.toString().split(',')
                        let seatSplit = dataT['seat'].split(',')
                        seatSplit.forEach((data) => {
                          seatSplitCurrent.forEach((element, idx) => {
                            if (data == element) {
                              this.chooseStore.add(element)
                            }
                          });
                        });

                      }
                    } else {
                      console.log("abc1")
                      const seatSplit = dataT['seat'].split(',')
                      seatSplit.forEach((data) => {
                        this.seatsDisable.push(data)



                        // console.log(this.chooseSeats)
                      });
                    }
                  }
                }
              }
            });
          }
        }
        );
      }
    });

  }
}
