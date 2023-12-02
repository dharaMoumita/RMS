import { Component, OnInit, ViewChild } from '@angular/core';
import { FoodService } from '../../services/food-services/food.service';
import { Observable } from 'rxjs';
import { FoodPayload } from '../../FoodPayload';
import { OrderFoodPayload } from '../../OrderFoodPayload';
import { OrderFoodService } from '../../services/food-services/order-food.service';
import { CustomerServiceService } from '../../services/customer-services/customer.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export default class MenuComponent implements OnInit{
  selectedFood:any={};

  orderItem:number;
  itemQuantity:number=0;
  orderFood:OrderFoodPayload[]=[];
  food:Observable<Array<FoodPayload>>;
constructor(private foodService:FoodService,private orderFoodService:OrderFoodService,private customerService:CustomerServiceService){

}
  ngOnInit()  {

    this.food=this.foodService.getAllFoods();
    this.orderFood=this.orderFoodService.getOrderFoodQuantity();
    this.orderItem=this.customerService.getCustomerRegistration();
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

  getQuantity(item_id:number):number{
    this.itemQuantity=this.orderFoodService.getQuantity(item_id);
    return this.itemQuantity; 
  }
  details(){


    this.onCloseModal();

  }
  @ViewChild('closebutton') closebutton;

  onCloseModal() {
    this.closebutton.nativeElement.click();
  }
  showFoodDetails(data:any){
    this.selectedFood.food=data.id;
    this.selectedFood.index=data.index;
    console.log(this.selectedFood);
    

  }


}
