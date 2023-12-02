import { Component, IterableDiffers, OnInit } from '@angular/core';
import { OrderFoodPayload } from '../../OrderFoodPayload';
import { FoodPayload } from '../../FoodPayload';
import { OrderFoodService } from '../../services/food-services/order-food.service';
import { FoodService } from '../../services/food-services/food.service';
import { OrderDetailsPayload } from '../../OrderDetailsPayload';
import { OrderDetailService } from '../../services/food-services/order-detail.service';
import { Router } from '@angular/router';
import { CustomerServiceService } from '../../services/customer-services/customer.service';

@Component({
  selector: 'app-order-detaiils',
  templateUrl: './order-detaiils.component.html',
  styleUrls: ['./order-detaiils.component.css'],
})
export class OrderDetaiilsComponent implements OnInit {
  private orderFoodQuantity: OrderFoodPayload[] = [];
  private foodItem: FoodPayload;
  public orderDetails: OrderDetailsPayload[] = [];
  public totalPrice: any;
  public orderId:any;
  public OrderDTO:any={};
  constructor(
    private orderFoodService: OrderFoodService,
    private foodService: FoodService,
    private orderDetailSErvice: OrderDetailService,
    private route:Router,private customerService:CustomerServiceService
  ) {}
  
  ngOnInit() {
    this.orderFoodQuantity = this.orderFoodService.getOrderFoodQuantity();
    console.log(this.orderFoodQuantity);
    
    if(this.orderFoodQuantity.length==0){

      alert("Sorry You dont have any orders")
      this.route.navigate(["home"]);
    }

    this.orderDetails = this.orderDetailSErvice.getOrderDetail(this.orderFoodQuantity);
    console.log(this.orderDetails);
    
  }

  addOrder() {
    this.OrderDTO = {
      date: new Date(),
      customerId:this.customerService.getCustomerRegistration(),
      orderFoodDTOList: this.orderFoodQuantity,
    };
    // this.foodService.addOrder(OrderDTO).subscribe((res) => {
    //   res.hasOwnP
    //   this.orderId=res;
    // });
    // console.log(this.orderId);
    if(localStorage.getItem('currentOrder')!=null){
      this.OrderDTO.id=+JSON.parse(localStorage.getItem('currentOrder'));
    }
    console.log(this.OrderDTO);
    if(!('id' in this.OrderDTO) ){
    if(confirm("Confirm Order")){
    this.foodService.addOrder(this.OrderDTO).then(response=>{
      console.log(response);
      this.orderId=response;
      localStorage.setItem("currentOrder", JSON.stringify(this.orderId));

      
    }).finally( ()=>{
      this.orderFoodService.removeAllProduct()
      this.route.navigate(["bill"]);
    })}
    else{
      this.route.navigate(['menu']);
    }
    
    }else{
      
      if(confirm("Confirm Order")){
        this.foodService.updateOrder(this.OrderDTO).toPromise().then(response=>{
          console.log(response);
          this.orderId=response;
          localStorage.setItem("currentOrder", JSON.stringify(this.orderId));
    
          
        }).finally( ()=>{
          this.orderFoodService.removeAllProduct()
          this.route.navigate(["bill"]);
        })}
        else{
          this.route.navigate(['menu']);
        }
      

    }
  }
}
