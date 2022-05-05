import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { faCalendarAlt } from '@fortawesome/free-solid-svg-icons';
import { NgSelectComponent } from '@ng-select/ng-select';
import { ConnectApiService } from '../../../Services/Web/connect-api.service';
import { FullTicket } from '../../../entity/ticket'
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { MaintenanceSystemComponent } from '../../../Modal/MaintenanceSystem/maintenance-system/maintenance-system.component';
import { Province } from 'src/app/entity/province';
import { FormatDate } from 'src/app/common/formatdate';
import { DeleteComponent } from 'src/app/Modal/Delete/delete/delete.component';
import { DeleteSuccessComponent } from 'src/app/Modal/Delete/delete-success/delete-success.component';
import { Router } from '@angular/router';
import { UpdateComponent } from '../../../Modal/ManagerTicket/update/update.component';
import { CommonModalComponent } from 'src/app/Modal/Common/common-modal/common-modal.component';
import { DetailComponent } from 'src/app/Modal/ManagerTicket/detail/detail.component';
@Component({
  selector: 'app-manager-ticket',
  templateUrl: './manager-ticket.component.html',
  styleUrls: ['./manager-ticket.component.scss']
})
export class ManagerTicketComponent implements OnInit {

  @ViewChild('NgSelectComponent') ngSelectComponent: NgSelectComponent;
  objectKeys = Object.keys;
  model: NgbDateStruct;
  faCalendar = faCalendarAlt;
  formSearchTicket: FormGroup;
  provinces: Province[]
  dataTable: FullTicket[]
  statuss: any;
  page;
  pageSize;
  totalElements
  columHeader =
    {
      fullName: "Họ và tên",
      phone: "Số điện thoại",
      ticketCode: 'Mã vé',
      dateBuyTicket: 'Ngày mua',
      dateGo: 'Ngày đi',
      route: 'Tuyến đường',
      price: 'Tổng tiền',
      status: 'Trạng thái'
    };
  fieldID = 'ticketID'

  constructor(private fb: FormBuilder, private connectApi: ConnectApiService, private modalService: NgbModal, private FormatDate: FormatDate, private Router: Router) {
    this.formSearchTicket = fb.group(
      {

        fullName: [null],
        phone: [null],
        ticketCode: [null],
        dateBuyTicket: [null],
        dateGo: [null],
        provinceStart: [null],
        provinceEnd: [null],
        statusID: [null]
      }
    )
  }

  ngOnInit(): void {
    this.connectApi.get('province/getall').subscribe((response) => {

      this.provinces = response['content'];
    }, err => {
      this.modalService.open(MaintenanceSystemComponent)
    });
    this.getAllDataTable()
    this.connectApi.get('statusticket/getall').subscribe((response) => {
      this.statuss = response['content'];
    })
  }
  action(event) {
    console.log(event)
    if (event.action == 'info') {
      const modalDetailRef = this.modalService.open(DetailComponent, { size: 'lg' })
      modalDetailRef.componentInstance.ticket = this.dataTable.filter(data => data.ticketID === event.value)
    } else if (event.action == 'update') {

      const modalRef = this.modalService.open(UpdateComponent, { size: 'xl' })
      modalRef.componentInstance.ticket = this.dataTable.filter(data => data.ticketID === event.value)
      modalRef.componentInstance.dataTicket.subscribe(data => {
        console.log("🚀 ~ file: manager-ticket.component.ts ~ line 84 ~ ManagerTicketComponent ~ action ~ data", data)
        let requestUpdateTicket
        if (data['seats']) {
          const request =
          {
            ticketID: data['ticketID'],
            ticketCode: data['codeTicket'],
            amountSeats: data['amout'],

            seat: data['seats'].join(','),
            price: data['price'],
            tripID: data['tripID'],

            statusID: data['statusID']
          }
          requestUpdateTicket = request
        }
        else {
          requestUpdateTicket = data
        }
        console.log("🚀 ~ file: manager-ticket.component.ts ~ line 87 ~ ManagerTicketComponent ~ action ~ requestUpdateTicket", requestUpdateTicket)

        this.connectApi.post('ticket/update', requestUpdateTicket).subscribe(
          (response) => {

            const commonModalRef = this.modalService.open(CommonModalComponent)
            commonModalRef.componentInstance.content = "Cập nhật thành công"
            commonModalRef.componentInstance.callBack.subscribe(data => {
              this.getAllDataTable()
            })
          },
          (err) => {
            console.log(err);
          }
        )

        console.log("🚀 ~ file: manager-ticket.component.ts ~ line 83 ~ ManagerTicketComponent ~ action ~ data", data)

        // const requestUpdateTicket =
        // {
        //   amountSeats: data['amountSeats'],
        //   price: data['price'],
        //   seat: data['seat'].join(','),
        //   statusID: 2,
        //   ticketCode: data['ticketCode'],
        //   tripID: data['tripID']
        // }
      })


    } else if (event.action == 'delete') {

      this.modalService.open(DeleteComponent).result.then(() => {
        const requestDeleteTicket =
        {
          ticketID: event.value
        }
        console.log('ss');
        this.connectApi.post('ticket/delete', requestDeleteTicket).subscribe(
          (response) => {

            this.modalService.open(DeleteSuccessComponent).result.then(() => {
              this.getAllDataTable()
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
                ticketID: iterator
              }

            );

          }
          this.connectApi.post('ticket/deletecheck', requestDeleteCheck).subscribe(
            (response) => {

              this.modalService.open(DeleteSuccessComponent).result.then(() => {
                this.getAllDataTable()
              })

            },
            (err) => {
              console.log(err);
            }
          )
        })
      }


    }
    else if (event.action == 'addNew') {
      this.Router.navigateByUrl('/');

    }
  }
  searchTicket() {
    let dateBuyTicket = this.FormatDate.format(this.formSearchTicket.value.dateBuyTicket)
    let dateGo = this.FormatDate.format(this.formSearchTicket.value.dateGo)
    const request =
    {
      ...this.formSearchTicket.value,
      dateBuyTicket: dateBuyTicket,
      dateGo: dateGo,
      info:
      {
        fullName: this.formSearchTicket.value.fullName,
        phone: this.formSearchTicket.value.phone,
      }

    }
    delete request.fullName
    delete request.phone
    this.connectApi.post('ticket/getticket', request).subscribe((response) => {
      console.log(response);
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
  getAllDataTable() {
    const request =
    {
      ...this.formSearchTicket.value,
      pageNum: this.page,
      pageSize: this.pageSize
    }
    this.connectApi.post('ticket/getticket', request).subscribe((response) => {

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
    this.getAllDataTable()
  }
  changeLimit(event) {
    this.pageSize = event;
    this.getAllDataTable()
  }
}
