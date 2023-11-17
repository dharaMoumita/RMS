import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { FoodPayload } from 'src/app/FoodPayload';
import { OrderFoodPayload } from 'src/app/OrderFoodPayload';

@Injectable({
  providedIn: 'root'
})
export class FoodService {

  PATH_OF_API = 'http://localhost:8082';


  constructor(private httpClient:HttpClient) { }

  public getAllFoods():Observable<Array<FoodPayload>>
    {
    return this.httpClient.get<Array<FoodPayload>>(this.PATH_OF_API + '/foods');
  }
  public  findFoodById(id:number):Observable<FoodPayload>{
    return this.httpClient.get<FoodPayload>(this.PATH_OF_API + '/foods/' + id);
  }

  public addOrder(orderFoodDTOList:any){
    return this.httpClient.post(this.PATH_OF_API+'/orderFood',orderFoodDTOList).toPromise();
  }

  public SetPayment(orderId:number){
    console.log(orderId);
    
    return this.httpClient.get(this.PATH_OF_API+"/orderFood/payment/"+orderId)
  }



}
