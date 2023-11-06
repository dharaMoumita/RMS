package com.example.rms.DAO;

import com.example.rms.Entity.Table_Reservation.Reservation;
import com.example.rms.Entity.Table_Reservation.Tables;
import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@EnableJpaRepositories
@Repository
public interface ReservationRepo extends JpaRepository<Reservation,Integer> {

    List<Reservation> findByreservedDate(Date date);
    @Query("SELECT r FROM Reservation r WHERE r.tables_details.id = :tableId")
        List<Reservation> findByTablesDetailsTableId(int tableId);
    //  Integer Integer public List<Reservationervation> findByReservationDate(Date date);
//    @Query("SELECT r FROM Reservation r WHERE r.reservedDate <> :date")
//    public List<Reservation> findReservationsByreservedDateNot(Date date);

}
