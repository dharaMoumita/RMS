package com.example.rms.Controller;


import com.example.rms.DTO.ReservationDetailsDTO;
import com.example.rms.DTO.TableReserveDTO;
import com.example.rms.DTO.TablesDTO;
import com.example.rms.Service.TableReservationService;
import com.example.rms.Service.TablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping
public class TablesController {

    @Autowired
    private TablesService tablesService;


    @PostMapping("/tables")
    public TablesDTO addTable(@RequestBody TablesDTO tablesDTO){
        System.out.println(tablesDTO);
        return tablesService.addTables(tablesDTO);
    }

    @GetMapping("/tables/{id}")
    public TablesDTO getTables(@PathVariable int id){
        return tablesService.getTables(id);
    }

    @GetMapping("/tables")
    public List<TablesDTO> getAllTables(){
        return tablesService.getAllTables();
    }

    @DeleteMapping("/tables/{id}")
    public TablesDTO deleteTables(@PathVariable int id){
        return tablesService.deleteTables(id);
    }

    @PutMapping("/tables/{id}")
    public TablesDTO updateTables(@RequestBody TablesDTO tablesDTO,@PathVariable int id){
        return tablesService.updateTables(tablesDTO,id);
    }




}
