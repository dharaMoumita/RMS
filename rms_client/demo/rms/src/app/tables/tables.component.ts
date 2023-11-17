import { Component, OnInit } from '@angular/core';
import { TaleService } from '../services/table-services/tale.service';
import { TablePayload } from '../TablePayload';
import { TableDetailsService } from '../services/table-services/table-details.service';
import { TableReservePayload } from '../TableReservePayload';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tables',
  templateUrl: './tables.component.html',
  styleUrls: ['./tables.component.css'],
})
export class TablesComponent implements OnInit {
  tableList: TablePayload[] = [];
  capacity: number;
  date: Date;
  tableReserveDetails: TableReservePayload;

  constructor(
    private taleService: TaleService,
    private tableDetailService: TableDetailsService,
    private router:Router
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
    console.log(details.tableData);
    this.tableList = details.tableData;
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
        tId:tableId,
      };
      this.tableDetailService.setTableReserveDetails(reserveDetailis);
       this.router.navigate(['customer-registration']);

    //   this.taleService
    //     .makeReservation(tableId, reserveDetailis)
    //     .subscribe((result) => {
    //       console.log(result);
    //     });
    // }



  }
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
