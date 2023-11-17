import { Component, OnInit } from '@angular/core';
import { TableDetailsService } from '../services/table-services/table-details.service';
import { CustomerServiceService } from '../services/customer-services/customer.service';
import { TaleService } from '../services/table-services/tale.service';
import { TablePayload } from '../TablePayload';
import { CustomerPayload } from '../CustomerPayload';
import { Router } from '@angular/router';

@Component({
  selector: 'app-reserve-tables',
  templateUrl: './reserve-tables.component.html',
  styleUrls: ['./reserve-tables.component.css'],
})
export class ReserveTablesComponent implements OnInit {

  tableReserveDetails: any;
  public tableDetail:TablePayload;
  public id:number;
  public tableno:number;
  public cap:number;
  public confirmed:boolean=false;
  public reserveDetails:any;
  public customerDetails:CustomerPayload;
  constructor(
    private tableDetailsService: TableDetailsService,
    private customerService: CustomerServiceService,
    private taleService:TaleService,
    private router:Router
  ) {}

  ngOnInit(): void {
    this.tableReserveDetails =
      this.tableDetailsService.getTableReserveDetails();
    console.log(this.tableReserveDetails);
    this.reserveDetails = {
      ...this.tableReserveDetails,
    };
   this.id=this.tableReserveDetails?.tId;
    console.log(this.id)
    // console.log(reserveDetailis);

    this.taleService.getTableById(this.id).subscribe(res=>{
      console.log(res);
      this.tableDetail=res;
    });
  }
  confirmReserve(){
    console.log(this.customerService.getCustomerRegistration());
    this.reserveDetails.customerId=this.customerService.getCustomerRegistration();
    delete this.reserveDetails['tId'];
    console.log(this.reserveDetails);
    
    this.taleService
        .makeReservation(this.id, this.reserveDetails)
        .toPromise().then((result) => {
          console.log(result);
          localStorage.setItem("currentReservation",JSON.stringify(result));
        }).finally(()=>{
          this.router.navigate(['table-bill']);

        });

  }
  

}

