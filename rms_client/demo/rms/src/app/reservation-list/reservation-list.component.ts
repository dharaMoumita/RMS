import { Component, OnInit } from '@angular/core';
import { TaleService } from '../services/table-services/tale.service';
import { UserService } from '../services/user-services/user.service';
import { CustomerServiceService } from '../services/customer-services/customer.service';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-reservation-list',
  templateUrl: './reservation-list.component.html',
  styleUrls: ['./reservation-list.component.css']
})
export class ReservationListComponent implements OnInit {

allReservation:any[]=[];
customerDetails:any[]=[];
data:any[]=[];
  constructor(private tableService:TaleService,private cusomerService:CustomerServiceService){}


  ngOnInit(): void {


    
    this.tableService.getAllReservationByUser().subscribe(res=>{
      this.allReservation=res;
      console.log(this.allReservation);
      const requests = res.map((customer) =>
      this.cusomerService.getUCustomer(customer['customerId'])
    );
    forkJoin(requests).subscribe((details) => {
      this.data = details.map((detail, index) => ({
        ...this.allReservation[index],
        customerName: detail['customerName'],
      }));

      console.log(this.data);
      
    })
    

  })
}

cancelReservation(arg0: any) {

  this.tableService.cancelReservation(arg0).subscribe(result=>{
    console.log(result);
    this.ngOnInit();
  })

  }

}
