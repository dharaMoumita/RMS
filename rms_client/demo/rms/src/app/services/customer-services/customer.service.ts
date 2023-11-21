import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CustomerServiceService {
  PATH_OF_API = 'http://localhost:8082';
  private customerId:any;

  constructor(private httpclient:HttpClient) { }

  public getUCustomer(userId:number):Observable<any>{
    return this.httpclient.get<any>(this.PATH_OF_API+"/customer/"+userId);
  }

  public getCustomerByPHone(phone:string):Observable<any>{
    return this.httpclient.get<any>(this.PATH_OF_API+"/customer/check/"+phone);
  }

  setCustomerRegistration(custId:any){
    this.customerId=custId;
  }

  getCustomerRegistration():any{
    
    return this.customerId;
  }


}
