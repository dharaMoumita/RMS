import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FoodService } from '../../services/food-services/food.service';
import { OrderDetailsPayload } from '../../OrderDetailsPayload';
import { OrderDetailService } from '../../services/food-services/order-detail.service';
import { UserService } from '../../services/user-services/user.service';
import { CustomerServiceService } from '../../services/customer-services/customer.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit{
  orderId:number;
  order:any={};
  orderList: any;
  orderDetails: OrderDetailsPayload[];
  createdBy: any;
  customerName: any;
  customerPhone: any;
  totalPrice: any;
  orderedDate: any;
  abc: any;
  constructor(private router:Router,private route:ActivatedRoute,private foodService:FoodService,private orderDetailService:OrderDetailService,private userServics:UserService,private customerSErvice:CustomerServiceService){}


  ngOnInit(): void {
    this.orderId=+this.route.snapshot.paramMap.get('id');
    this.foodService.getOrderById(this.orderId).subscribe(resonse=>{
      this.orderList = resonse['orderFoodDTOList'];
      console.log(this.orderList);

      this.orderDetails = this.orderDetailService.getOrderDetail(
        this.orderList
      );
      console.log(this.orderDetails);
        this.order['orderDetails']=this.orderDetails;
      // this.userServics.getUser(resonse['userId']).subscribe((res) => {
      //   console.log(res);
      //   this.order['createdBy'] = res['userName'];
      // });
      this.customerSErvice
        .getUCustomer(resonse['customerId'])
        .subscribe((res) => {
          console.log(res);
          this.order['customerName'] = res['customerName'];
          this.order['customerPhone'] = res['phone'];
        });
      this.order['payment']=resonse['payment']
      this.order['orderedDate'] = resonse['date'];
      this.order['totalPrice'] = resonse['price'];
      console.log(this.order);

    });

    
    
  }
  confirmPayment(){
    this.foodService.SetPayment(this.orderId).toPromise().then(()=>{
      alert("Payment Completed")
    }).finally(()=>{
    this.router.navigate(['user']);
    });
  }
  generateBill(){
    localStorage.setItem("currentOrder", JSON.stringify(this.orderId));
    this.router.navigate(['bill']);

  }
}
