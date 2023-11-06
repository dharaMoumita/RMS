import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { CustomerPayload } from '../CustomerPayload';

@Component({
  selector: 'app-customer-registration',
  templateUrl: './customer-registration.component.html',
  styleUrls: ['./customer-registration.component.css']
})
export class CustomerRegistrationComponent {
customerPayload:CustomerPayload;

  PATH_OF_API = 'http://localhost:8082';

  constructor(private httpClient:HttpClient){}

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
        
      })
    }
    }

}
