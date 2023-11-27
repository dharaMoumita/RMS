import { Component, OnDestroy, OnInit, Output, Renderer2, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { CustomerCheckComponent } from '../../customer-check/customer-check.component';
import { FoodService } from '../../services/food-services/food.service';
import { EventEmitter } from '@angular/core';


@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit{
  
todayOrders:number;
allOrders:number;
todayEarning:number=0;
allEarning:number=0;
pendingOrders:any []=[];
pending:number=0;
orders:any[]=[];
all:any[]=[];
today:any[]=[];
  constructor(private router:Router, private dialogRef : MatDialog, private foodSErvice:FoodService,private renderer: Renderer2){}
    
  

goto() {
  this.dialogRef.open(CustomerCheckComponent,{
    "width": '600px',
    "maxHeight": '160vh',
    "data": "John",
    "autoFocus": true,
    panelClass: ['custom-dialog-class']

  });
  // this.router.navigate(['customer-check']);

}
ngOnInit(): void {
    this.foodSErvice.getTodayOrder().subscribe(res=>{
      console.log(res);
      this.todayOrders=res.length;
      res.forEach(ele=>{
        console.log(typeof(ele['price']));
        this.today.push(ele);
        this.todayEarning+=ele['price'] as number;
      })
      console.log(this.todayOrders);
      console.log(this.todayEarning);
      
      
    })
    this.foodSErvice.getALlOrder().subscribe(res=>{
      console.log(res);
      this.allOrders=res.length;
      res.forEach(ele=>{
        this.all.push(ele);
        console.log(typeof(ele['price']));
        
        this.allEarning+=ele['price'] as number;
      })
      console.log(this.allOrders);
      console.log(this.allEarning);
      
      res.forEach(ele=>{
        if(ele['payment']==false){
          this.pendingOrders.push(ele);
        }
      })
      this.pending=this.pendingOrders.length;
      console.log(this.pendingOrders);
      
    })

}

getPendingOrders(){
  this.orders=[];

  this.orders=this.pendingOrders;
}
getTodayOrders(){
  this.orders=[];

  this.orders=this.today;
}
getAllOrders(){
  this.orders=[];
  this.orders=this.all;
}
viewOrder(orderId:number){
  this.router.navigate(['order', orderId ]);
  this.onCloseModal();
}
@ViewChild('closebutton') closebutton;


onCloseModal(){
  this.closebutton.nativeElement.click();
}

}
