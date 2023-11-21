import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { CustomerPayload } from '../CustomerPayload';
import { OrderFoodService } from '../services/food-services/order-food.service';
import { Router } from '@angular/router';
import { CustomerServiceService } from '../services/customer-services/customer.service';
import { UserService } from '../services/user-services/user.service';

@Component({
  selector: 'app-customer-registration',
  templateUrl: './customer-registration.component.html',
  styleUrls: ['./customer-registration.component.css']
})
export class CustomerRegistrationComponent {
customerPayload:CustomerPayload;
custId: any;
  PATH_OF_API = 'http://localhost:8082';

  constructor(private httpClient:HttpClient, private orderfoodService:OrderFoodService,private router:Router,
    public userService: UserService
    ,private customerService:CustomerServiceService){}
    
  register(cusomerForm: NgForm) {
    if(cusomerForm.valid){
      console.log(cusomerForm.value);
      this.customerPayload=new CustomerPayload(cusomerForm.value.email,cusomerForm.value.username,cusomerForm.value.phone);
      console.log(this.customerPayload);
      
      // this.customerPayload.birtdate=cusomerForm.value.birthdate.;
      // this.customerPayload.customerName=cusomerForm.value.username;
      // this.customerPayload.email=cusomerForm.value.email;
      // this.customerPayload.phone=cusomerForm.value.phone;
      // console.log(this.customerPayload);
      
      this.httpClient.post(this.PATH_OF_API+"/customer",this.customerPayload).subscribe(result=>{
        console.log(result);
        const cusId=result;
        this.customerService.setCustomerRegistration(cusId);
        
      });
      if(this.userService.roleMatch(['Waiter'])){
      this.router.navigate(['menu'])
      }else if(this.userService.roleMatch(['Manager'])){
        this.router.navigate(['reserve-tables']);

      }
    }
    }

}
