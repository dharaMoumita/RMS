import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { CustomerCheckComponent } from '../customer-check/customer-check.component';
import { FoodService } from '../services/food-services/food.service';


@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit{
  
todayOrders:number;
allOrders:number;
todayEarning:number=0;
allEarning:number=0;
  constructor(private router:Router, private dialogRef : MatDialog, private foodSErvice:FoodService){}

goto() {
  this.dialogRef.open(CustomerCheckComponent,{
    "width": '600px',
    "maxHeight": '160vh',
    "data": "John",
    "autoFocus": true,
    panelClass: ['custom-dialog-class']

  });
  // this.router.navigate(['customer-check']);

}
ngOnInit(): void {
    this.foodSErvice.getTodayOrder().subscribe(res=>{
      console.log(res);
      this.todayOrders=res.length;
      res.forEach(ele=>{
        console.log(typeof(ele['price']));
        
        this.todayEarning+=ele['price'] as number;
      })
      console.log(this.todayOrders);
      console.log(this.todayEarning);
      
      
    })
    this.foodSErvice.getALlOrder().subscribe(res=>{
      console.log(res);
      this.allOrders=res.length;
      res.forEach(ele=>{
        console.log(typeof(ele['price']));
        
        this.allEarning+=ele['price'] as number;
      })
      console.log(this.allOrders);
      console.log(this.allEarning);
      
      
    })

}


}
