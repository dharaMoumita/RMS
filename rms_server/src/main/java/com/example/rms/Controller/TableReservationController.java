package com.example.rms.Controller;

import com.example.rms.DTO.ReservationDetailsDTO;
import com.example.rms.DTO.TableReserveDTO;
import com.example.rms.Entity.Table_Reservation.Tables;
import com.example.rms.Service.TableReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin
public class TableReservationController {

    @Autowired
    private TableReservationService tableReservationService;
    @PostMapping("/reserveTable")
    public int makeReservation(@RequestBody TableReserveDTO tableReserveDTO){
        return tableReservationService.makeReservation(tableReserveDTO).getReservationId();
    }

    @GetMapping("/reserveTable/{id}")
    public  ReservationDetailsDTO getReservationById(@PathVariable int id){
        return tableReservationService.getByReservationId(id);
    }

    @GetMapping("/reserveTable")
    public List<ReservationDetailsDTO> getReservationByUser(){
        return tableReservationService.getReservationByUser();
    }

    @GetMapping("/reserveTable/cancel/{id}")
    public ReservationDetailsDTO cancelReservation(@PathVariable int id){
        return  tableReservationService.cancelReservation(id);
    }

    @GetMapping("/availableTables/{capacity}/{reserveDate}")
    public List<Tables> getAllAvialableTables(@PathVariable String capacity, @PathVariable String reserveDate)throws Exception{
        System.out.println(reserveDate);
        System.out.println(capacity);
        return tableReservationService.tablesAvailable(capacity,reserveDate);
    }

//    @GetMapping("/availbleTables")
//    public List<Tables> getAllAvailableTables(@RequestBody TableReserveDTO tableReserveDTO){
//        return tableReservationService.tablesAvailable(tableReserveDTO);
//    }

    @GetMapping("/availableTables/capacity/{capacity}")
    public List<Tables> getTablesByCapacity(@PathVariable int capacity){
        return tableReservationService.getByCapacity(capacity);
    }


    @GetMapping("/availableTables/date/{date}")
    public List<Tables> getTablesByDate(@PathVariable String date)throws  Exception{
        return tableReservationService.findByDate(Date.valueOf(date));
    }

    @PostMapping("/reserveTable/{id}")
    public int reserveTablesById(@PathVariable int id,@RequestBody TableReserveDTO tableReserveDTO)throws Exception {
        System.out.println(tableReserveDTO);
        return tableReservationService.reserveTableById(id,tableReserveDTO);
    }


}
