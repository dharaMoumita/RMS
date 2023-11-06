import { FoodPayload } from "./FoodPayload";

export class OrderDetailsPayload{
    foodItem:FoodPayload;
    quantity:number;
    price:number;
}