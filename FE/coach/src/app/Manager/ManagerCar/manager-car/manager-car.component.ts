import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CommonModalComponent } from 'src/app/Modal/Common/common-modal/common-modal.component';
import { DeleteSuccessComponent } from 'src/app/Modal/Delete/delete-success/delete-success.component';
import { DeleteComponent } from 'src/app/Modal/Delete/delete/delete.component';
import { AddnewAndUpdateCarComponent } from 'src/app/Modal/Manager/MagagerCar/addnew-and-update-car/addnew-and-update-car.component';
import { ConnectApiService } from 'src/app/Services/Web/connect-api.service';

@Component({
  selector: 'app-manager-car',
  templateUrl: './manager-car.component.html',
  styleUrls: ['./manager-car.component.scss']
})
export class ManagerCarComponent implements OnInit {
  formSearchCar: FormGroup
  dataTable;
  page;
  pageSize;
  totalElements
  columHeader =
    {
      code: "Mã xe",
      name: "Tên xe",
      licensePlates: 'Biển số xe',
      price: 'Giá',
      amount: 'số lượng chỗ ngồi',
      statusName: 'Trạng thái'
    };
  fieldID = 'carID'
  constructor(private fb: FormBuilder, private connectApi: ConnectApiService, private modalService: NgbModal) {

    this.formSearchCar = this.fb.group(
      {
        code: [null],
        name: [null],
        licensePlates: [null],
        price: [null]
      }
    )
  }

  ngOnInit(): void {

    this.getDataTable();
  }


  searchRoute() {
    this.getDataTable();

  }
  action(event) {

    console.log(event)
    if (event.action == 'delete') {
      this.modalService.open(DeleteComponent).result.then(() => {
        const requestDeleteTicket =
        {
          carID: event.value
        }
        this.connectApi.post('car/delete', requestDeleteTicket).subscribe(
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
    else if (event.action == 'deleteAll') {
      if (event.value.size === 0) {

      }
      else {
        this.modalService.open(DeleteComponent).result.then(() => {
          const requestDeleteCheck = []
          for (const iterator of event.value) {
            requestDeleteCheck.push(
              {
                carID: iterator
              }

            );
          }
          this.connectApi.post('car/deletecheck', requestDeleteCheck).subscribe(
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

      const updateModalRef = this.modalService.open(AddnewAndUpdateCarComponent)
      updateModalRef.componentInstance.isUpdate = true;
      updateModalRef.componentInstance.title = "Cập nhật"
      updateModalRef.componentInstance.carID = event.value

      updateModalRef.result.then(response => {
        const requestUpdate = {
          ...response,
          carID: event.value
        }
        this.connectApi.post('car/update', requestUpdate).subscribe(
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


      const addNewModalRef = this.modalService.open(AddnewAndUpdateCarComponent)
      addNewModalRef.componentInstance.carID = event.value
      addNewModalRef.componentInstance.title = "Thêm mới"
      addNewModalRef.result.then(response => {

        this.connectApi.post('car/save', response).subscribe(
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
  pageChange(event) {
    this.page = event
    this.getDataTable()
  }
  changeLimit(event) {
    this.pageSize = event;
    this.getDataTable()
  }
  getDataTable()
  {

    const request =
    {
      ...this.formSearchCar.value,
      pageNum: this.page,
      pageSize: this.pageSize
    }
    this.connectApi.post('car/searchcar', request).subscribe((response) => {

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

}
