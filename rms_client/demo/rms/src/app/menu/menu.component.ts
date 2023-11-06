import { Component, OnInit } from '@angular/core';
import { FoodService } from '../services/food-services/food.service';
import { Observable } from 'rxjs';
import { FoodPayload } from '../FoodPayload';
import { OrderFoodPayload } from '../OrderFoodPayload';
import { OrderFoodService } from '../services/food-services/order-food.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export default class MenuComponent implements OnInit{

  orderItem=0;
  orderFood:OrderFoodPayload[]=[];
  food:Observable<Array<FoodPayload>>;
constructor(private foodService:FoodService,private orderFoodService:OrderFoodService){

}
  ngOnInit()  {

    this.food=this.foodService.getAllFoods();
    this.orderFood=this.orderFoodService.getOrderFoodQuantity();

    // this.foodService.getAllFoods().subscribe(result=>{
    //   console.log(result);
    // },
    // (err)=>{
    //   console.log(err);
      
    // })


  }

  add(id:number){
    

    this.orderFoodService.addProduct(id);
    this.orderFood=this.orderFoodService.getOrderFoodQuantity();
    console.log(this.orderFood);
    

  }

  remove(id:number){
    this.orderFoodService.removeProduct(id);
    this.orderFood=this.orderFoodService.getOrderFoodQuantity();
    console.log(this.orderFood);
    
  }




}
