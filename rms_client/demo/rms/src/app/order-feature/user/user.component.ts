import {
  Component,
  OnDestroy,
  OnInit,
  Output,
  Renderer2,
  ViewChild,
} from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { CustomerCheckComponent } from '../../customer-check/customer-check.component';
import { FoodService } from '../../services/food-services/food.service';
import { EventEmitter } from '@angular/core';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
})
export class UserComponent implements OnInit {
  todayOrders: number;
  allOrders: number;
  todayEarning: number = 0;
  allEarning: number = 0;
  pendingOrders: any[] = [];
  pending: number = 0;
  orders: any[] = [];
  all: any[] = [];
  today: any[] = [];
  todayDate:any;
  pendingCheck=false;
  todayCheck: boolean;
  allCheck: boolean;
  
  constructor(
    private router: Router,
    private dialogRef: MatDialog,
    private foodSErvice: FoodService,
    private renderer: Renderer2
  ) {}

  goto() {
    this.dialogRef.open(CustomerCheckComponent, {
      width: '600px',
      maxHeight: '160vh',
      data: 'John',
      autoFocus: true,
      panelClass: ['custom-dialog-class'],
    });
    // this.router.navigate(['customer-check']);
  }
  ngOnInit(): void {
    this.todayDate=new Date().toISOString().split('T')[0];
    console.log(this.todayDate);
    this.foodSErvice.getTodayOrder().subscribe((res) => {
      console.log(res);
      res.forEach((ele) => {
        if (ele['payment'] == true) {
          this.today.push(ele);
          this.todayEarning += ele['price'] as number;
        }
      });
      this.todayOrders = this.today.length;

      console.log(this.todayOrders);
      console.log(this.todayEarning);
    });
    this.foodSErvice.getALlOrder().subscribe((res) => {
      console.log(res);
      res.forEach((ele) => {
        this.all.push(ele);
        console.log(typeof ele['price']);

        if (ele['payment'] == true) {
          this.allEarning += ele['price'] as number;
        }
      });
      this.allOrders = this.all.length;

      console.log(this.allOrders);
      console.log(this.allEarning);

      res.forEach((ele) => {
        if (ele['payment'] == false) {
          this.pendingOrders.push(ele);
        }
      });
      this.pending = this.pendingOrders.length;
      console.log(this.pendingOrders);
    });
  }
  showOrders(date:any) {
    this.orders=[];
    console.log(date);
    this.todayDate=date;
    this.foodSErvice.getOrderDatewise(date).toPromise().then((res)=>{
      res.forEach((ele)=>{
          if(this.pendingCheck==true){
            if (ele['payment'] == false) {
              this.orders.push(ele);
            }
    

          }else if(this.todayCheck==true){
            if (ele['payment'] == true) {
              this.orders.push(ele);
            }
          }
          else if(this.allCheck==true){
            if (ele['payment'] == true) {
              this.orders.push(ele);
            }
          }
      })
    })
    
  }
  getPendingOrders() {
    this.orders = [];
    this.pendingCheck=true;
    this.orders = this.pendingOrders;
  }
  getTodayOrders() {
    this.orders = [];
    this.todayCheck=true;
    this.orders = this.today;
  }
  getAllOrders() {
    this.orders = [];
    this.allCheck=true;
    this.orders = this.all;
  }
  viewOrder(orderId: number) {
    this.router.navigate(['order', orderId]);
    this.onCloseModal();
  }
  @ViewChild('closebutton') closebutton;

  onCloseModal() {
    this.closebutton.nativeElement.click();
  }
}
