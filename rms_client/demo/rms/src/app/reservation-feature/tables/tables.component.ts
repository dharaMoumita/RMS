import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { TablePayload } from 'src/app/TablePayload';
import { TableReservePayload } from 'src/app/TableReservePayload';
import { CustomerCheckComponent } from 'src/app/customer-check/customer-check.component';
import { TableDetailsService } from 'src/app/services/table-services/table-details.service';
import { TaleService } from 'src/app/services/table-services/tale.service';

@Component({
  selector: 'app-tables',
  templateUrl: './tables.component.html',
  styleUrls: ['./tables.component.css'],
})
export class TablesComponent implements OnInit {
  tableList: TablePayload[] = [];
  tableAvailable: TablePayload[] = [];

  capacity: number;
  date: Date;
  tableReserveDetails: TableReservePayload;

  constructor(
    private taleService: TaleService,
    private tableDetailService: TableDetailsService,
    private router: Router,
    private dialogRef: MatDialog
  ) {}

  ngOnInit(): void {
    this.taleService.getAllTables().subscribe((res) => {
      console.log(res);

      this.tableList = res;
    });

  }

  getData() {
    const tableReserveDetails: TableReservePayload = {
      reqCapcity: this.capacity,
      reserveDate: this.date,
    };
  }

  showTables(details: {
    tableData: TablePayload[];
    inputData: TableReservePayload;
  }) {
    this.taleService.getAllTables().subscribe((res) => {
      console.log(res);

      this.tableList = res;
    });
  

    console.log(details.tableData);
    this.tableAvailable = details.tableData;
    this.tableReserveDetails = details.inputData;
    console.log(this.tableList);
  }

  remove(tableId: number) {
    if (
      this.tableReserveDetails.reqCapcity == null ||
      this.tableReserveDetails.reserveDate == undefined ||
      this.tableReserveDetails.reqCapcity == undefined
    )
      alert('Please add requied fields');
    else {
      const reserveDetailis: any = {
        ...this.tableReserveDetails,
        tId: tableId,
      };
      this.tableDetailService.setTableReserveDetails(reserveDetailis);

      this.dialogRef.open(CustomerCheckComponent, {
        width: '600px',
        maxHeight: '160vh',
        autoFocus: true,
        panelClass: ['custom-dialog-class'],
      });
      // this.router.navigate(['customer-check']);
    }

    //   this.taleService
    //     .makeReservation(tableId, reserveDetailis)
    //     .subscribe((result) => {
    //       console.log(result);
    //     });
    // }
  }
  getColor(table:TablePayload):any{
    const found= this.tableAvailable.find(
      t=>
      t.id==table.id
    );
    return found?true:false;
  }

  
}

// if (
//   tableReserveDetails.reqCapcity == null &&
//   tableReserveDetails.reserveDate != undefined
// ) {
//   this.taleService
//     .getAllTablesAvailableByReservedDate(tableReserveDetails.reserveDate)
//     .subscribe((result) => {
//       console.log(result);
//       this.tableList = result;
//       this.tableDetailService.setTableDetails(result);
//     });
// } else if (
//   tableReserveDetails.reqCapcity != null &&
//   tableReserveDetails.reserveDate == undefined
// ) {
//   this.taleService
//     .getAllTablesAvailableByCapacity(tableReserveDetails.reqCapcity)
//     .subscribe((result) => {
//       console.log(result);
//       this.tableList = result;

//       this.tableDetailService.setTableDetails(result);
//     });
// } else if (
//   tableReserveDetails.reqCapcity != null &&
//   tableReserveDetails.reserveDate != null
// ) {
//   this.taleService
//     .getAllTablesAvailableByCriteria(
//       tableReserveDetails.reserveDate,
//       tableReserveDetails.reqCapcity
//     )
//     .subscribe((result) => {
//       console.log(result);
//       this.tableList = result;

//       this.tableDetailService.setTableDetails(result);
//     });
// } else {
//   this.taleService.getAllTables().subscribe((result) => {
//     console.log(result);
//     this.tableList = result;

//     this.tableDetailService.setTableDetails(result);
//   });
// }

// console.log(this.tableDetailService.getTableDetails());
