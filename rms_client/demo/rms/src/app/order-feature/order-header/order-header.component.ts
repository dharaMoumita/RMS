import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-order-header',
  templateUrl: './order-header.component.html',
  styleUrls: ['./order-header.component.css']
})
export class OrderHeaderComponent implements OnInit{
  maxDate:any;
  date:Date;
  valueDate:any;
  reqDate:any;
  @Output() newItemEvent = new EventEmitter<any>();

  constructor(){

  }
  ngOnInit(): void {
    this.maxDate=new Date().toISOString().split('T')[0];
    this.valueDate=this.maxDate;
    console.log(this.maxDate);

  }
  getData(){
    this.reqDate=this.date;
    console.log(this.reqDate);
    this.newItemEvent.emit(this.reqDate);

    
  }

}
