package com.example.rms.DAO;

import com.example.rms.Entity.Table_Reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

public interface ReservationRepo extends JpaRepository<Reservation,Integer> {

    @Query("SELECT r FROM Reservation r WHERE r.reservedDate = :reservedDate")
    List<Reservation> findReservedDate(@Param("reservedDate") Date reservedDate);

}
