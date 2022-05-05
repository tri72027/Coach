import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Car } from 'src/app/entity/car';
import { Province } from 'src/app/entity/province';
import { ConnectApiService } from 'src/app/Services/Web/connect-api.service';
import { faCalendarAlt } from '@fortawesome/free-solid-svg-icons';
import { FormatDate } from 'src/app/common/formatdate';
import { MaintenanceSystemComponent } from 'src/app/Modal/MaintenanceSystem/maintenance-system/maintenance-system.component';
import { DeleteComponent } from 'src/app/Modal/Delete/delete/delete.component';
import { DeleteSuccessComponent } from 'src/app/Modal/Delete/delete-success/delete-success.component';
import { CommonModalComponent } from 'src/app/Modal/Common/common-modal/common-modal.component';
import { AddnewAndUpdateTripComponent } from 'src/app/Modal/ManagerTrip/addnew-and-update-trip/addnew-and-update-trip.component';

@Component({
  selector: 'app-manager-trip',
  templateUrl: './manager-trip.component.html',
  styleUrls: ['./manager-trip.component.scss']
})
export class ManagerTripComponent implements OnInit {
  faCalendar = faCalendarAlt;
  formSearchTrip: FormGroup;
  provinces: Province[];
  cars: Car[];
  routes
  statuss;
  page;
  pageSize;
  totalElements
  dataTable;
  columHeader =
    {
      date: "ngày đi",
      route: "Tuyến đường",
      licensePlates: "Biển số xe",
      departureTime: 'Giờ khởi hành',
      journeyTime: 'Thời gian chạy',
      statusName: 'Trạng thái'
    };

  fieldID = 'tripID';


  constructor(private fb: FormBuilder, private connectApi: ConnectApiService, private modalService: NgbModal, private FormatDate: FormatDate) {
    this.formSearchTrip = this.fb.group(
      {
        date: [null],
        routeID: [null],
        carID: [null],
        statusID: [null]
      }
    )

  }

  ngOnInit(): void {
    this.connectApi.get("route/searchdropdownroute").subscribe(response => {
      this.routes = response['content'];
    }, err => {
      this.modalService.open(MaintenanceSystemComponent)
    });
    this.connectApi.get("statustrip/getall").subscribe(response => {
      this.statuss = response['content'];
    }, err => {
      this.modalService.open(MaintenanceSystemComponent)
    });
    this.connectApi.get("car/getall").subscribe(response => {
      this.cars = response['content'];
    }, err => {
      this.modalService.open(MaintenanceSystemComponent)
    });

    this.getDataTable();
  }

  searchTrip() {
    this.getDataTable();
  }
  pageChange(event) {
    this.page = event
    this.getDataTable()
  }
  changeLimit(event) {
    this.pageSize = event;
    this.getDataTable()
  }
  getDataTable() {
    const request =
    {
      ...this.formSearchTrip.value,
      pageNum: this.page,
      pageSize: this.pageSize,
      date: this.FormatDate.format(this.formSearchTrip.value.date)
    }
    this.connectApi.post('trip/gettrip', request).subscribe((response) => {
      this.dataTable = response['content']['data'];
      this.page = response['content']['pageNum'];
      this.pageSize = response['content']['pageSize'];
      this.totalElements = response['content']['totalElements']
    });
  }

  action(event) {


    console.log(event)
    if (event.action == 'delete') {
      this.modalService.open(DeleteComponent).result.then(() => {
        const requestDeleteTrip =
        {
          tripID: event.value
        }
        this.connectApi.post('trip/delete', requestDeleteTrip).subscribe(
          (response) => {

            this.modalService.open(DeleteSuccessComponent).result.then(() => {
              this.getDataTable()
            })

          },
          (err) => {
            console.log(err);
          }
        )
      })
    } else if (event.action == 'deleteAll') {
      if (event.value.size === 0) {

      }
      else {
        this.modalService.open(DeleteComponent).result.then(() => {
          const requestDeleteCheck = []
          for (const iterator of event.value) {
            requestDeleteCheck.push(
              {
                tripID: iterator
              }

            );
          }
          this.connectApi.post('ticket/deletecheck', requestDeleteCheck).subscribe(
            (response) => {

              this.modalService.open(DeleteSuccessComponent).result.then(() => {
                this.getDataTable()
              })
            },
            (err) => {
              console.log(err);
            }
          )
        })
      }

    }
    else if (event.action == 'update') {
      const modalUpdate = this.modalService.open(AddnewAndUpdateTripComponent)
      modalUpdate.componentInstance.isUpdate = true;
      modalUpdate.componentInstance.title = "Cập nhật"
      modalUpdate.componentInstance.tripID = event.value;
      modalUpdate.result.then(responseUpdate => {
        const requestUpdateTrip =
        {
          ...responseUpdate,
          date : this.FormatDate.format(responseUpdate['date']),
          tripID: event.value
        }

        console.log("🚀 ~ file: manager-trip.component.ts ~ line 162 ~ ManagerTripComponent ~ action ~ requestUpdateTrip", requestUpdateTrip)
        this.connectApi.post('trip/update', requestUpdateTrip).subscribe(
          (response) => {
            const commonModalRef = this.modalService.open(CommonModalComponent)
            commonModalRef.componentInstance.content = "Cập nhật thành công"

            commonModalRef.result.then(responseAddNewSucss => {
              this.getDataTable();
            })

          },
          (err) => {
            console.log(err);
          }
        )

      })
    }

    else if (event.action == 'addNew') {
      const modalAddNew = this.modalService.open(AddnewAndUpdateTripComponent)
      modalAddNew.componentInstance.title = "Thêm mới"
      modalAddNew.result.then(responseAddNew => {
        const requestAddNewTrip =
        {
          ...responseAddNew,
          date : this.FormatDate.format(responseAddNew['date']),
          tripID: event.value
        }
        this.connectApi.post('trip/save', requestAddNewTrip).subscribe(
          (response) => {
            const commonModalRef = this.modalService.open(CommonModalComponent)
            commonModalRef.componentInstance.content = "Thêm mới thành công"

            commonModalRef.result.then(responseAddNewSucss => {
              this.getDataTable();
            })
          },
          (err) => {
            console.log(err);
          }
        )
      })

    }
  }
}
