import { Component, IterableDiffers, OnInit } from '@angular/core';
import { OrderFoodPayload } from '../OrderFoodPayload';
import { FoodPayload } from '../FoodPayload';
import { OrderFoodService } from '../services/food-services/order-food.service';
import { FoodService } from '../services/food-services/food.service';
import { OrderDetailsPayload } from '../OrderDetailsPayload';

@Component({
  selector: 'app-order-detaiils',
  templateUrl: './order-detaiils.component.html',
  styleUrls: ['./order-detaiils.component.css']
})
export class OrderDetaiilsComponent implements OnInit{
  
  private orderFoodQuantity:OrderFoodPayload[]=[];
  private foodItem:FoodPayload;
  public  orderDetails:OrderDetailsPayload[]=[];
  public totalPrice:number;
  constructor(private orderFoodService:OrderFoodService,private foodService:FoodService,){

  }
  addOrder(){

    const OrderDTO={
      date:new Date().toLocaleDateString().slice(0, 10)
      ,
      orderFoodDTOList:this.orderFoodQuantity

    }
    this.foodService.addOrder(OrderDTO).subscribe((res)=>{
    console.log(res);
    });
         
  }
  ngOnInit(){
    this.orderFoodQuantity=this.orderFoodService.getOrderFoodQuantity();
    this.orderFoodQuantity.forEach(item=>{
      this.foodService.findFoodById(item.food_id).subscribe(result=>{
        this.foodItem=result;
        // console.log(this.foodItem);
        
        const order:OrderDetailsPayload={foodItem:this.foodItem,quantity:item.quantity,price:(this.foodItem.price*item.quantity)};
      // console.log(order);
        this.totalPrice+=result.price*item.quantity;
      this.orderDetails.push(order);
        
      });
    
    //  abc.unsubscribe();
      
      
    });
    //  console.log(this.orderDetails.at(0).price);
    //  console.log(this.totalPrice);
     
    
  }





}
