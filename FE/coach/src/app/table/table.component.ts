import { Component, Input, OnInit, Output, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { faPenAlt, faTrashAlt, faInfo } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent implements OnInit, OnChanges {
  faPenAlt = faPenAlt;
  faTrashAlt = faTrashAlt;
  faInfo = faInfo;
  datas;
  @Input() columHeader: any;
  @Input() dataTable: any;
  @Input() isAction: boolean;
  @Input() info = true;
  @Input() delete = true;
  @Input() update = true;
  @Input() isCheck = true
  @Input() fieldID: string;
  @Output() buttonAction = new EventEmitter<any>();
  @Output() pagi = new EventEmitter<any>();
  @Output() changeLimit = new EventEmitter<any>();
  @Input() page
  @Input()  pageSize
  @Input()  totalElements
  pageOpiton = [1,3,5,20,30,50]

  checkStore = new Set();
  checked: boolean
  objectKeys = Object.keys;
  requestDeleteAll
  constructor() { }
  ngOnChanges(changes: SimpleChanges): void {
    if ("dataTable" in changes) {
      this.dataTable = changes['dataTable'].currentValue


    }
    if ("page" in changes) {
      this.page = changes['page'].currentValue


    }
    if ("pageSize" in changes) {
      this.pageSize = changes['pageSize'].currentValue


    }
    if ("totalElements" in changes) {
      this.totalElements = changes['totalElements'].currentValue


    }

  }

  ngOnInit(): void {

  }
  action(value: any, action: string) {
    this.buttonAction.emit({ value, action })
  }
  checkAll(event) {

    this.dataTable.forEach(element => {
      if (event.target.checked) {

        this.checkStore.add(element[this.fieldID])
      }
      else {
        this.checkStore.delete(element[this.fieldID])

      }
    });
  }
  checkItem(id, event) {


    if (event.target.checked) {
      this.checkStore.add(id)
    }
    else {
      this.checkStore.delete(id)
    }
    if (this.checkStore.size === this.dataTable.length) {
      this.checked = true
    }
    else {
      this.checked = false
    }
  }

  pageChange(event) {
    console.log(event)
    this.page = event
    this.pagi.emit( this.page)
  }


  changePageSize(event) {
    console.log(event)
    this.pageSize = event.target.value
    this.changeLimit.emit(this.pageSize)
  }
}
