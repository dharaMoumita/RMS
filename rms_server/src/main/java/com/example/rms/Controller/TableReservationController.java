package com.example.rms.Controller;

import com.example.rms.DTO.ReservationDetailsDTO;
import com.example.rms.DTO.TableReserveDTO;
import com.example.rms.Entity.Table_Reservation.Tables;
import com.example.rms.Service.TableReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin
public class TableReservationController {

    @Autowired
    private TableReservationService tableReservationService;
    @PostMapping("/reserveTable")
    public ReservationDetailsDTO makeReservation(@RequestBody TableReserveDTO tableReserveDTO){
        return tableReservationService.makeReservation(tableReserveDTO);
    }

    @PutMapping("/reserveTable/{id}")
    public ReservationDetailsDTO cancelReservation(@PathVariable int id){
        return  tableReservationService.cancelReservation(id);
    }

    @GetMapping("/availableTables/{capacity}/{reserveDate}")
    public List<Tables> getAllAvialableTables(@PathVariable int capacity, @PathVariable Date reserveDate){
        return tableReservationService.tablesAvailable(capacity,reserveDate);
    }
}
