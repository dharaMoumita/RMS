import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { OrderFoodPayload } from '../OrderFoodPayload';
import { OrderDetailService } from '../services/food-services/order-detail.service';
import { OrderDetailsPayload } from '../OrderDetailsPayload';
import { UserService } from '../services/user-services/user.service';
import { CustomerServiceService } from '../services/customer-services/customer.service';
import { FoodService } from '../services/food-services/food.service';
import { Router } from '@angular/router';
import jsPDF from 'jspdf';
import { CanComponentDeactivate } from '../auth/bill-deactivate.guard';
@Component({
  selector: 'app-bill',
  templateUrl: './bill.component.html',
  styleUrls: ['./bill.component.css'],
})
export class BillComponent implements OnInit, CanComponentDeactivate {
  PATH_OF_API = 'http://localhost:8082';
  currentOrderId: number;
  orderList: any;
  orderDetails: OrderDetailsPayload[];
  createdBy: any;
  customerName: any;
  customerPhone: any;
  totalPrice: any;
  orderedDate: any;
  abc: any;
  hasUnsavedChanges = false;

  @ViewChild('biigenerator', { static: false }) el!: ElementRef;

  constructor(
    private routr: Router,
    private foodService: FoodService,
    private httpClient: HttpClient,
    private orderDetailService: OrderDetailService,
    private userServics: UserService,
    private customerSErvice: CustomerServiceService
  ) {}
  canDeactivate(): boolean {
    if (this.hasUnsavedChanges) {
      return true;
    }
    return false;
  }

  ngOnInit(): void {
    this.currentOrderId = +JSON.parse(localStorage.getItem('currentOrder'));
    console.log(this.currentOrderId);
    if(this.currentOrderId!=0){
    this.abc = this.httpClient
      .get(this.PATH_OF_API + '/orderFood/' + this.currentOrderId)
      .toPromise()
      .then((resonse) => {
        console.log(resonse);

        this.orderList = resonse['orderFoodDTOList'];
        console.log(this.orderList);

        this.orderDetails = this.orderDetailService.getOrderDetail(
          this.orderList
        );
        console.log(this.orderDetails);

        this.userServics.getUser(resonse['userId']).subscribe((res) => {
          console.log(res);
          this.createdBy = res['userName'];
        });
        this.customerSErvice
          .getUCustomer(resonse['customerId'])
          .subscribe((res) => {
            console.log(res);
            this.customerName = res['customerName'];
            this.customerPhone = res['phone'];
          });

        this.orderedDate = resonse['date'];
        this.totalPrice = resonse['price'];
      });

    console.log(this.orderList);
    }
    else{
      this.exit();
    }
  }
  exit() {
    this.hasUnsavedChanges = true;
    localStorage.removeItem('currentOrder');
    this.customerSErvice.setCustomerRegistration(null);
    this.routr.navigate(['home']);
  }

  confirmExit() {
    this.hasUnsavedChanges = true;
    this.foodService
      .SetPayment(this.currentOrderId)
      .toPromise()
      .then(() => {
        localStorage.removeItem('currentOrder');
        this.orderDetails = this.orderDetails.splice(
          0,
          this.orderDetails.length
        );
      })
      .finally(() => {
        this.customerSErvice.setCustomerRegistration(null);

        this.routr.navigate(['menu']);
      });
  }

  generatePDF() {



    this.foodService.SetPayment(this.currentOrderId).subscribe(()=>{
      console.log("Payment Confirmed");
      
    });
    const pdfWidth = 1200; // Width of an A4 paper
    const pdfHeight = 1200; // Height of an A4 paper

    // Create a new jsPDF instance with the specified dimensions
    const pdf = new jsPDF({
      unit: 'mm',
      format: [pdfWidth, pdfHeight],
    });

    pdf.html(this.el.nativeElement, {
      callback: (pdf) => {
        pdf.save('Bill' + this.currentOrderId);
      },
    });
    console.log('Generated');

    //   const element = document.getElementById('bii-generator'); // Replace with your HTML content element's ID
    //   html2pdf()
    //     .from(element)
    //     .outputPdf()
    //     .then(pdf => {
    //       // Now you can print the generated PDF
    //       pdf.output('dataurlnewwindow');
    //     });
    //     console.log("Generated");

    // }
  }
  
}
