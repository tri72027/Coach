import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NgSelectComponent } from '@ng-select/ng-select';
import { FormatDate } from 'src/app/common/formatdate';
import { Province } from 'src/app/entity/province';
import { CommonModalComponent } from 'src/app/Modal/Common/common-modal/common-modal.component';
import { DeleteSuccessComponent } from 'src/app/Modal/Delete/delete-success/delete-success.component';
import { DeleteComponent } from 'src/app/Modal/Delete/delete/delete.component';
import { MaintenanceSystemComponent } from 'src/app/Modal/MaintenanceSystem/maintenance-system/maintenance-system.component';
import { AddnewAndUpdateRouteComponent } from 'src/app/Modal/ManagerRoute/addnew-and-update-route/addnew-and-update-route.component';

import { ConnectApiService } from 'src/app/Services/Web/connect-api.service';

@Component({
  selector: 'app-manager-route',
  templateUrl: './manager-route.component.html',
  styleUrls: ['./manager-route.component.scss']
})
export class ManagerRouteComponent implements OnInit {
  @ViewChild('NgSelectComponent') ngSelectComponent: NgSelectComponent;
  formSearchRoute: FormGroup
  provinces: Province[];
  dataTable;
  statuss
  page;
  pageSize;
  totalElements
  columHeader =
    {
      provinceStartName: "Điểm đi",
      provinceEndName: "Điểm đến",
      departureTime: 'Giờ khởi hành',
      journeyTime: 'Thời gian chạy',
      price: 'Giá',
      statusName: 'Trạng thái'
    };
  fieldID = 'routeID'
  constructor(private fb: FormBuilder, private connectApi: ConnectApiService, private modalService: NgbModal, private FormatDate: FormatDate) {

    this.formSearchRoute = this.fb.group(
      {
        departureTime: [null],
        journeyTime: [null],
        provinceStart: [null],
        provinceEnd: [null],
        statusID: [null]
      }
    )
  }
  ngOnInit(): void {
    this.connectApi.get("province/getall").subscribe(response => {
      this.provinces = response['content'];
    }, err => {
      this.modalService.open(MaintenanceSystemComponent)
    });
    this.connectApi.get("status/getall").subscribe(response => {
      this.statuss = response['content'];
    }, err => {
      this.modalService.open(MaintenanceSystemComponent)
    });

    this.getDataTable()
  }

  action(event) {

    console.log(event)
    if (event.action == 'delete') {
      this.modalService.open(DeleteComponent).result.then(() => {
        const requestDeleteTicket =
        {
          routeID: event.value
        }
        this.connectApi.post('route/delete', requestDeleteTicket).subscribe(
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
                routeID: iterator
              }

            );
          }
          this.connectApi.post('route/deletecheck', requestDeleteCheck).subscribe(
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
      const updateModalRef = this.modalService.open(AddnewAndUpdateRouteComponent)
      updateModalRef.componentInstance.isUpdate = true;
      updateModalRef.componentInstance.title = "Cập nhật"
      updateModalRef.componentInstance.routeID = event.value

      updateModalRef.result.then(response => {
        const requestUpdate = {
          ...response,
          routeID: event.value
        }
        this.connectApi.post('route/update', requestUpdate).subscribe(
          (response) => {

            const commonModalRef = this.modalService.open(CommonModalComponent)
            commonModalRef.componentInstance.content = "Cập nhật thành công"

            commonModalRef.result.then(responseUpdateSucss => {
              this.getDataTable();
            })
          })
      }, err => {
        console.log("error")
      })
    }
    else if (event.action == 'addNew') {

      const AddNewModalRef = this.modalService.open(AddnewAndUpdateRouteComponent)
      AddNewModalRef.componentInstance.routeID = event.value
      AddNewModalRef.componentInstance.title = "Thêm mới"
      AddNewModalRef.result.then(response => {

        this.connectApi.post('route/save', response).subscribe(
          (response) => {

            const commonModalRef = this.modalService.open(CommonModalComponent)
            commonModalRef.componentInstance.content = "Thêm mới thành công"

            commonModalRef.result.then(responseAddNewSucss => {
              this.getDataTable();
            })
          })
      }, err => {
        console.log("error")
      })
    }
  }
  searchRoute() {

    this.getDataTable()
  }
  getDataTable() {
    const request =
    {
      ...this.formSearchRoute.value,
      pageNum: this.page,
      pageSize: this.pageSize
    }
    this.connectApi.post("route/searchroute", request).subscribe(response => {

      if (response['content'] === null) {
        this.dataTable = null

      }
      else {
        this.dataTable = response['content']['data'];
        this.page = response['content']['pageNum'];
        this.pageSize = response['content']['pageSize'];
        this.totalElements = response['content']['totalElements']
      }


    })
  }
  pageChange(event) {
    this.page = event
    this.getDataTable()
  }
  changeLimit(event) {
    this.pageSize = event;
    this.getDataTable()
  }

}
