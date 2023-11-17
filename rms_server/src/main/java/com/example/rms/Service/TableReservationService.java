package com.example.rms.Service;

import com.example.rms.DTO.ReservationDetailsDTO;
import com.example.rms.DTO.TableReserveDTO;
import com.example.rms.Entity.Table_Reservation.Tables;

import java.sql.Date;
import java.util.List;

public interface TableReservationService {

//    public boolean tableAvailable(int id);
//    public int reserveTableById(int id,int custid) throws Exception;

    public ReservationDetailsDTO makeReservation(TableReserveDTO tableReserveDTO);
    public List<Tables> tablesAvailable(String capacity, String date) throws Exception;

    public ReservationDetailsDTO cancelReservation(int id);

    public List<Tables> getByCapacity(int capacity);

    public List<Tables> findByDate(Date date);
    public ReservationDetailsDTO getByReservationId(int id);

        List<Tables> tablesAvailable(TableReserveDTO tableReserveDTO);

    int reserveTableById(int id, TableReserveDTO tableReserveDTO) throws Exception;

    List<ReservationDetailsDTO> getReservationByUser();
}
