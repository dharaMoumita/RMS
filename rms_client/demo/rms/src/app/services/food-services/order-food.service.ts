import { Injectable } from '@angular/core';
import { OrderFoodPayload } from 'src/app/OrderFoodPayload';

@Injectable({
  providedIn: 'root'
})
export class OrderFoodService {
  private  orderFoodQuantity:OrderFoodPayload[]=[];
  constructor() { }
  

  addProduct(id:number){
    const existinfFoods=this.orderFoodQuantity.find(item=>item.food_id==id);
    if(existinfFoods){
      existinfFoods.quantity++;
    }
    else{
      const food: OrderFoodPayload={food_id:id,quantity:1};
      this.orderFoodQuantity.push(food);
    }
  }

  removeProduct(id:number){
    const existinfFoods=this.orderFoodQuantity.find(item=>item.food_id==id);
    if(existinfFoods.quantity>1){
      existinfFoods.quantity--;
    }else if(existinfFoods.quantity===1){
      const index = this.orderFoodQuantity.indexOf(existinfFoods);
      this.orderFoodQuantity.splice(index,1);
    }
    else{
      alert("Please add product to delete");

    }

  }

  getOrderFoodQuantity():OrderFoodPayload[]{
    return this.orderFoodQuantity;
  }
}
