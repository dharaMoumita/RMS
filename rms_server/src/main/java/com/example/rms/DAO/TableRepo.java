package com.example.rms.DAO;

import com.example.rms.Entity.Table_Reservation.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@EnableJpaRepositories
@Repository
public interface TableRepo extends JpaRepository<Tables,Integer> {

    public List<Tables> findALlTablesByCapacity(int capacity);


//    @Query("SELECT t FROM Tables t WHERE t.capacity = :tableCapacity AND t NOT IN (SELECT r.table FROM Reservation r WHERE r.reservedDate = :reservationDate)")
//    List<Tables> findAvailableTables(@Param("tableCapacity") int tableCapacity, @Param("reservationDate") Date reservationDate);


}
