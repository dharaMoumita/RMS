package com.example.rms.Service;

import com.example.rms.DTO.FoodDTO;
import com.example.rms.DTO.TablesDTO;

import java.util.List;

public interface TablesService {

    public TablesDTO addTables (TablesDTO tablesDTO);
    public TablesDTO deleteTables(int id);
    public TablesDTO updateTables(TablesDTO tablesDTO,int id);
    public TablesDTO getTables(int id);
    public List<TablesDTO> getAllTables();


}
