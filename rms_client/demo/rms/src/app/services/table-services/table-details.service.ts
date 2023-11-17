import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { TablePayload } from 'src/app/TablePayload';

@Injectable({
  providedIn: 'root'
})
export class TableDetailsService {
  private tableDeatils:TablePayload[]=[];
  private tableReserveDetails:any;


  constructor() { }



  setTableDetails(details:any){
    if(this.tableDeatils.length>0){
      this.tableDeatils=this.tableDeatils.splice(0,this.tableDeatils.length);
    }
    this.tableDeatils=details;
    console.log(this.tableDeatils);
    
    
  }

  getTableDetails():TablePayload[]{
    console.log(this.tableDeatils);
    
    return this.tableDeatils;
  }
  setTableReserveDetails(tableReserve:any){
    this.tableReserveDetails=tableReserve;
  }

  getTableReserveDetails():any{
    return this.tableReserveDetails;
  }

}
