import { Component, OnInit } from '@angular/core';
import { TaleService } from '../services/table-services/tale.service';
import { UserService } from '../services/user-services/user.service';
import { CustomerServiceService } from '../services/customer-services/customer.service';
import { forkJoin } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-reservation-list',
  templateUrl: './reservation-list.component.html',
  styleUrls: ['./reservation-list.component.css']
})
export class ReservationListComponent implements OnInit {
  currentUserId:number;
  currentReserveId:number;
allReservation:any[]=[];
customerDetails:any[]=[];
data:any[]=[];
details:any={};
  // customerName: any;
  // customerPhone: any;
  // tableNo: any;
  // reservationDate: any;
  status: boolean;
  // totalPrice: any;
  // capacity: any;
  // reservationId: string;
  constructor(private tableService:TaleService,private userSErvice:UserService,private cusomerService:CustomerServiceService,private route:ActivatedRoute,private router:Router){}


  ngOnInit(): void {

    this.userSErvice.getCurrentUser().subscribe((res)=>{
      this.currentUserId=res;
      console.log(res);
      
    })

    this.currentReserveId=+this.route.snapshot.paramMap.get('id');
    console.log(this.currentReserveId);
    
   this.tableService.getREservationByID(this.currentReserveId).toPromise().then((resonse)=>{
      console.log(resonse);
      
    
        
        // this.userServics.getUser(resonse['userId']).subscribe(res=>{
        //   console.log(res);
        //   this.createdBy=res['userName'];
        // });
        this.cusomerService.getUCustomer(resonse['customerId']).subscribe(res=>{
          console.log(res);
          this.details.customerName=res['customerName'];
          this.details.customerPhone=res['phone']
        })
        this.details.tableNo=resonse['tableNo'];
        this.details.reservationDate= resonse['reservationDate'];
        this.status=resonse['status']
        this.details.status=resonse['status']?"Reserved":"Cancelled";
        this.details.charges=resonse['charges'];
        this.details.capacity=resonse['capacity'];
        this.details.reservationId=this.details.tableNo+""+resonse['reservationId'];
        this.details.reserveUserId=+resonse['userId'];
        
    
   })

    
  //   this.tableService.getAllReservationByUser().subscribe(res=>{
  //     this.allReservation=res;
  //     console.log(this.allReservation);
  //     const requests = res.map((customer) =>
  //     this.cusomerService.getUCustomer(customer['customerId'])
  //   );
  //   forkJoin(requests).subscribe((details) => {
  //     this.data = details.map((detail, index) => ({
  //       ...this.allReservation[index],
  //       customerName: detail['customerName'],
  //     }));

  //     console.log(this.data);
      
  //   })
    

  // })
}

cancelReservation() {

  this.tableService.cancelReservation(this.currentReserveId).subscribe(result=>{
    console.log(result);
  })

  }
  generateBill(){
    localStorage.setItem("currentReservation", JSON.stringify(this.currentReserveId));
    this.router.navigate(['table-bill']);

  }

}
