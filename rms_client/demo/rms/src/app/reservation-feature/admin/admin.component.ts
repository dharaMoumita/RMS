import { Component, ViewChild } from '@angular/core';
import { forkJoin } from 'rxjs';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { CustomerServiceService } from 'src/app/services/customer-services/customer.service';
import { TaleService } from 'src/app/services/table-services/tale.service';
import { UserService } from 'src/app/services/user-services/user.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
})
export class AdminComponent {
  // constructor(private router:Router){}
  // goto() {
  //   this.router.navigate(['customer-registration']);

  // }
  currentDate: string;
  reservation:any[]=[];
  allReservation: any[] = [];
  todayReservation:any[]=[];
  todayALlReservation:any[]=[];
  todayAll:number;
  all:number;
  today:number;
  customerDetails: any[] = [];
  data: any[] = [];
  currentUserId:number;
  constructor(
    private tableService: TaleService,
    private cusomerService: CustomerServiceService,
    private router:Router,
    private userService:UserService,
    private datePipe: DatePipe
  ) {
    const dateObj = new Date();
    this.currentDate = this.datePipe.transform(dateObj, 'yyyy-MM-dd');
    console.log(this.currentDate);
  }

  ngOnInit(): void {
    this.tableService.getAllReservationByUser().subscribe((res) => {
      this.allReservation = res;
      this.all=this.allReservation.length;
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
      });
    });
    this.tableService.getReservationBYDate().subscribe(res=>{
      console.log(res);
      this.todayReservation=res;
      this.today=this.todayReservation.length;
      
    })
    this.tableService.getAllTablesAvailableByReservedDate(this.currentDate).subscribe(res=>{
        this.todayALlReservation=res;
        console.log(this.todayALlReservation);
        this.todayAll=this.todayALlReservation.length;
        
        
    })
    
  }
  

  cancelReservation(arg0: any) {
    this.tableService.cancelReservation(arg0).subscribe((result) => {
      console.log(result);
    });
  }
  @ViewChild('closebutton') closebutton;


onCloseModal(){
  this.closebutton.nativeElement.click();
}
getAllReservation(){
  this.reservation=[];
  this.reservation=this.allReservation;
}
getTodayReservation(){
  this.reservation=[];
  this.reservation=this.todayALlReservation;

}
viewReservation(reservationId:number){
  this.router.navigate(['reservation-list', reservationId ]);
  this.onCloseModal();
}

}
