import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TablePayload } from 'src/app/TablePayload';
import { TableReservePayload } from 'src/app/TableReservePayload';

@Injectable({
  providedIn: 'root'
})
export class TaleService {
  PATH_OF_API = 'http://localhost:8082';

  constructor(private httpClient:HttpClient) { }

  public getAllTables():Observable<Array<TablePayload>>{
    return this.httpClient.get<Array<TablePayload>>(this.PATH_OF_API+"/tables");
  }


  public getTableById(table:number):Observable<TablePayload>{
    return this.httpClient.get<TablePayload>(this.PATH_OF_API+"/tables/"+table);

  }

  public getAllReservationByUser():Observable<Array<TablePayload>>{
    return this.httpClient.get<Array<TablePayload>>(this.PATH_OF_API+"/reserveTable");
  }


  public getTablesAvailable(tableReserveDetails: TableReservePayload):Observable<Array<TablePayload>>{
    return this.httpClient.get<Array<TablePayload>>(this.PATH_OF_API+"/availableTables/"+tableReserveDetails.reqCapcity+"/"+tableReserveDetails.reserveDate);
}

  public cancelReservation(reservationId:number):Observable<any>{
    return this.httpClient.get<any>(this.PATH_OF_API+"/reserveTable/cancel/"+reservationId);
  }

  // public getAllTablesAvailableByCapacity(capacity:number):Observable<Array<TablePayload>>{
  //   return this.httpClient.get<Array<TablePayload>>(this.PATH_OF_API+"/availableTables/capacity/"+capacity);
  // }

  // public getAllTablesAvailableByReservedDate(reservedDate:Date):Observable<Array<TablePayload>>{
  //   return this.httpClient.get<Array<TablePayload>>(this.PATH_OF_API+"/availableTables/date/"+reservedDate);
  // }

  // public getAllTablesAvailableByCriteria(reservedDate:Date,capacity:number):Observable<Array<TablePayload>>{
  //   return this.httpClient.get<Array<TablePayload>>(this.PATH_OF_API+"/availableTables/"+capacity+"/"+reservedDate);
  // }

  public makeReservation(tableId:number,reserveDetails:any):Observable<number>{
    return this.httpClient.post<number>(this.PATH_OF_API + "/reserveTable/" + tableId, reserveDetails);
  }




}
