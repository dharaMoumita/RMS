import { Component } from '@angular/core';
import { forkJoin } from 'rxjs';
import { CustomerServiceService } from '../services/customer-services/customer.service';
import { TaleService } from '../services/table-services/tale.service';

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

  allReservation: any[] = [];
  customerDetails: any[] = [];
  data: any[] = [];
  constructor(
    private tableService: TaleService,
    private cusomerService: CustomerServiceService
  ) {}

  ngOnInit(): void {
    this.tableService.getAllReservationByUser().subscribe((res) => {
      this.allReservation = res;
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
  }

  cancelReservation(arg0: any) {
    this.tableService.cancelReservation(arg0).subscribe((result) => {
      console.log(result);
    });
  }
}
