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
import { TableDetailsService } from '../services/table-services/table-details.service';
import { CanComponentDeactivate } from '../auth/bill-deactivate.guard';
@Component({
  selector: 'app-table-bill',
  templateUrl: './table-bill.component.html',
  styleUrls: ['./table-bill.component.css']
})
export class TableBillComponent implements OnInit,CanComponentDeactivate{
  PATH_OF_API = 'http://localhost:8082';
  currentReserveId:number;
  reservationId:any;
  createdBy:any;
  customerName:any;
  customerPhone:any;
  totalPrice:any;
  tableNo:number;
  reservationDate:Date;
  capacity:number;
  status:any;
  hasUnsavedChanges=false;
  abc: any;
  @ViewChild('biigenerator',{static:false}) el!:ElementRef;
  

  constructor(private routr:Router,private httpClient:HttpClient,private userServics:UserService,private customerSErvice:CustomerServiceService, private tabledetails:TableDetailsService){

    
    }
  canDeactivate (): boolean{
    if(this.hasUnsavedChanges){
      return true;
    }
    return false;
  }
  

    
  ngOnInit(): void {
    this.currentReserveId=+(JSON.parse(localStorage.getItem("currentReservation")));
    console.log(this.currentReserveId);
    
   this.abc= this.httpClient.get(this.PATH_OF_API+"/reserveTable/"+this.currentReserveId).toPromise().then((resonse)=>{
      console.log(resonse);
      
    
        
        this.userServics.getUser(resonse['userId']).subscribe(res=>{
          console.log(res);
          this.createdBy=res['userName'];
        });
        this.customerSErvice.getUCustomer(resonse['customerId']).subscribe(res=>{
          console.log(res);
          this.customerName=res['customerName'];
          this.customerPhone=res['phone']
        })
        this.tableNo=resonse['tableNo'];
        this.reservationDate= resonse['reservationDate'];
        this.status=resonse['status']?"Reserved":"Unresreved";
        this.totalPrice=resonse['charges'];
        this.capacity=resonse['capacity'];
        this.reservationId=this.tableNo+""+resonse['reservationId'];
    
   })
    

  }


  
  exit() {
    this.hasUnsavedChanges=true;
    localStorage.removeItem("currentReservation");
    this.tabledetails.setTableReserveDetails([]);
    this.routr.navigate(["home"]);


    }
    
    generatePDF() {
      
      const pdfWidth = 1200; // Width of an A4 paper
      const pdfHeight = 1200; // Height of an A4 paper
  
      // Create a new jsPDF instance with the specified dimensions
      const pdf = new jsPDF({
        unit: 'mm',
        format: [pdfWidth, pdfHeight],
      });
       
      pdf.html(this.el.nativeElement,{
        callback:(pdf)=>{
          pdf.save("Bill"+ this.currentReserveId);
        }
      })
      console.log("Generated");
      
    }

    
  // confirmExit() {
  //     this.foodService.SetPayment(this.currentOrderId).toPromise().then(()=>{
  //         localStorage.removeItem("currentOrder");
  //         this.orderDetails=this.orderDetails.splice(0,this.orderDetails.length);
  //     }).finally(()=>{
  //       this.routr.navigate(["menu"]);
  //     })

  //   }
  //   title="pdfGenerator";
     
  //   generatePDF() {
      
  //     const pdfWidth = 1200; // Width of an A4 paper
  //     const pdfHeight = 1200; // Height of an A4 paper
  
  //     // Create a new jsPDF instance with the specified dimensions
  //     const pdf = new jsPDF({
  //       unit: 'mm',
  //       format: [pdfWidth, pdfHeight],
  //     });
       
  //     pdf.html(this.el.nativeElement,{
  //       callback:(pdf)=>{
  //         pdf.save("Bill"+ this.currentOrderId);
  //       }
  //     })
  //     console.log("Generated");
      


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

    // {tableNo: 3, reservationId: 9, reservationDate: '2023-11-13', status: true, charges: 400, …}