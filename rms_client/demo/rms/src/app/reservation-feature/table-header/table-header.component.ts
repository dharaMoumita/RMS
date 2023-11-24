import { Component, EventEmitter, Output } from '@angular/core';
import { TaleService } from '../../services/table-services/tale.service';
import { TableDetailsService } from '../../services/table-services/table-details.service';
import { TableReservePayload } from '../../TableReservePayload';
import { TablePayload } from '../../TablePayload';

@Component({
  selector: 'app-table-header',
  templateUrl: './table-header.component.html',
  styleUrls: ['./table-header.component.css'],
})
export class TableHeaderComponent {
  capacity: number;
  date: Date;
  minDate:any;
  data:TablePayload[]=[];
  @Output() newItemEvent = new EventEmitter<{tableData: TablePayload[],inputData:TableReservePayload}>();

  constructor(
    private taleService: TaleService,
    private tableDetailService: TableDetailsService
  ) {

     this.minDate=new Date().toISOString().split('T')[0];
    console.log(this.minDate);
    }


  getData() {
    const tableReserveDetails: TableReservePayload = {
      reqCapcity: this.capacity,
      reserveDate: this.date,
    };

    console.log(tableReserveDetails);
this.taleService.getTablesAvailable(tableReserveDetails).subscribe(result=>{
  console.log(result);
  this.data=result;
  this.newItemEvent.emit({tableData:this.data,inputData:tableReserveDetails});

})




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
//       this.abc=result;
//       this.abc();
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
//       this.tableDetailService.setTableDetails(result);
//     });
// } else {
//   this.taleService.getAllTables().subscribe((result) => {
//     console.log(result);
//     this.tableDetailService.setTableDetails(result);
//   });
// }

// console.log(this.tableDetailService.getTableDetails());
