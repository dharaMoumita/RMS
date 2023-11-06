package com.example.rms.Service;

import com.example.rms.DTO.ReservationDetailsDTO;
import com.example.rms.DTO.TableReserveDTO;
import com.example.rms.Entity.Table_Reservation.Tables;

import java.util.Date;
import java.util.List;

public interface TableReservationService {

    public boolean tableAvailable(int id);
    public ReservationDetailsDTO makeReservation(TableReserveDTO tableReserveDTO);
    public List<Tables> tablesAvailable(int capacity, Date date);

    public ReservationDetailsDTO cancelReservation(int id);
}
