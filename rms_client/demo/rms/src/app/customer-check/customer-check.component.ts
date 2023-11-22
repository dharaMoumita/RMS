import { Component, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerServiceService } from '../services/customer-services/customer.service';
import { UserService } from '../services/user-services/user.service';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-customer-check',
  templateUrl: './customer-check.component.html',
  styleUrls: ['./customer-check.component.css']
})
export class CustomerCheckComponent {

  phone: string;

constructor(private router:Router, private dialogRef : MatDialog,private customerSErvice:CustomerServiceService,private userService:UserService){}


checkCustomer() {
  this.customerSErvice.getCustomerByPHone(this.phone).subscribe(result=>{
    if(result){
      console.log(result['id']);

      this.customerSErvice.setCustomerRegistration(result['id']);
      if(this.userService.roleMatch(['Waiter'])){
        this.router.navigate(['menu'])
        }else if(this.userService.roleMatch(['Manager'])){
          this.router.navigate(['reserve-tables']);
  
        }
  
    }else{
      this.router.navigate(['customer-registration']);
    }
    
  })
  this.dialogRef.closeAll();

}

}
