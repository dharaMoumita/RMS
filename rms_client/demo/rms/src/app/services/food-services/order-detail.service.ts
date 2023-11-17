import { Injectable } from '@angular/core';
import { FoodPayload } from 'src/app/FoodPayload';
import { OrderDetailsPayload } from 'src/app/OrderDetailsPayload';
import { OrderFoodPayload } from 'src/app/OrderFoodPayload';
import { FoodService } from './food.service';
import { OrderFoodService } from './order-food.service';

@Injectable({
  providedIn: 'root',
})
export class OrderDetailService {
  // private orderFoodQuantity: OrderFoodPayload[];
  private foodItem: FoodPayload;
  // public orderDetails: OrderDetailsPayload[];

  constructor(private foodService: FoodService) {}

  getOrderDetail(orderFood: OrderFoodPayload[]): OrderDetailsPayload[] {
    console.log(orderFood);
    const o: OrderDetailsPayload[] = [];
    orderFood.forEach((item) => {
      this.foodService.findFoodById(item.food_id).subscribe((result) => {
        this.foodItem = result;
        const order: OrderDetailsPayload = {
          foodItem: this.foodItem,
          quantity: item.quantity,
          price: this.foodItem.price * item.quantity,
        };
        o.push(order);
      });
    });
    return o;
  }
}
