import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { CustomerCheckComponent } from '../customer-check/customer-check.component';


@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {
  

  constructor(private router:Router, private dialogRef : MatDialog){}

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


}
