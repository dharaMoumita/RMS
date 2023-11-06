package com.example.rms.Service.ServiceImpl;


import com.example.rms.DTO.TablesDTO;
import com.example.rms.Entity.Table_Reservation.Tables;
import com.example.rms.DAO.TableRepo;
import com.example.rms.Service.TablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TablesServiceImpl implements TablesService {

    @Autowired
    private TableRepo tableRepo;

    @Override
    public TablesDTO addTables(TablesDTO tablesDTO) {
        Tables tables=TabblesDTOtoTables(tablesDTO);
        tableRepo.save(tables);
        return TablestoTablesDTO(tables);    }

    @Override
    public TablesDTO deleteTables(int id) {
        Tables tables=tableRepo.findById(id).get();
        TablesDTO tablesDTO=TablestoTablesDTO(tables);
        tableRepo.delete(tables);
        return tablesDTO;
    }

    @Override
    public TablesDTO updateTables(TablesDTO tablesDTO, int id) {
        Tables tables=tableRepo.findById(id).get();
        Tables tables1=TabblesDTOtoTables(tablesDTO);
        tables1.setId(id);
        tableRepo.save(tables1);
        return TablestoTablesDTO(tables1);    }

    @Override
    public TablesDTO getTables(int id) {
        Tables tables=tableRepo.findById(id).get();
        return TablestoTablesDTO(tables);
    }

    @Override
    public List<TablesDTO> getAllTables() {
        List<Tables> tablesList=tableRepo.findAll();
        List<TablesDTO> tablesDTOList=new ArrayList<>();
        for(Tables i:tablesList){
            tablesDTOList.add(TablestoTablesDTO(i));
        }

        return tablesDTOList;
    }

    private Tables TabblesDTOtoTables(TablesDTO tablesDTO){
        Tables tables=new Tables();
        tables.setTableNo(tablesDTO.getTableNo());
        tables.setCapacity(tablesDTO.getCapacity());
        return tables;
    }

    private TablesDTO TablestoTablesDTO(Tables tables){
        TablesDTO tablesDTO=new TablesDTO();
        tablesDTO.setId(tables.getId());
        tablesDTO.setCapacity(tables.getCapacity());
        tablesDTO.setTableNo(tables.getTableNo());
        return tablesDTO;
    }
}
