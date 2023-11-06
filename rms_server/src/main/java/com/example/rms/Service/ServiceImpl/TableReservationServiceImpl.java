package com.example.rms.Service.ServiceImpl;

import com.example.rms.DAO.ReservationRepo;
import com.example.rms.DAO.TableRepo;
import com.example.rms.DAO.UserRepo;
import com.example.rms.DTO.ReservationDetailsDTO;
import com.example.rms.DTO.TableReserveDTO;
import com.example.rms.DTO.TablesDTO;
import com.example.rms.Entity.Auth.User;
import com.example.rms.Entity.Table_Reservation.Reservation;
import com.example.rms.Entity.Table_Reservation.Tables;
import com.example.rms.Service.TableReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TableReservationServiceImpl implements TableReservationService {


    @Autowired
    private ReservationRepo reservationRepo;

    @Autowired
    private TableRepo tableRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public boolean tableAvailable(int id) {
        return false;
    }

//    public int makeReservation(TableReserveDTO tableReserveDTO){
//        List<Tables> tablesList=tableRepo.findALlTablesByCapacity(tableReserveDTO.getReqCapcity());
//        List<Tables> tableAvilable=getTableAvailableByDate(tableReserveDTO.getReserveDate());
//        Reservation reservation = new Reservation();
//
//        for(Tables i: tablesList){
//            Reservation reservation1=reservationRepo.findBytables_details(i.getId()).get();
//        }
//            reservation.setReservedDate(tableReserveDTO.getReserveDate());
//            reservation.setReserved(true);
//    reservation.setTables_details(tableAvilable.get(0));
////        tableAvilable.get(0).setTable_reserve();
//        Reservation abc=reservationRepo.save(reservation);
////        tableRepo.save(tableAvilable.get(0));
//
//        System.out.println("......................................................................Reserved");
//
//        return abc.getId();

//    }
    public List<Tables> tablesAvailable(int capacity,Date date){
        List<Reservation> reservationList=reservationRepo.findByreservedDate(date);
        List<Tables> tablesList=tableRepo.findALlTablesByCapacity(capacity);
        for(Reservation i:reservationList){
            if(tablesList.contains(i.getTables_details()) && i.isReserved()==true){
                tablesList.remove(i.getTables_details());
            }
            if(tablesList.isEmpty()){
                break;
            }
        }
        return tablesList;
    }
    public ReservationDetailsDTO makeReservation(TableReserveDTO tableReserveDTO){
        List<Reservation> reservationList=reservationRepo.findByreservedDate(tableReserveDTO.getReserveDate());
        List<Tables> tablesList=tableRepo.findALlTablesByCapacity(tableReserveDTO.getReqCapcity());
        for(Reservation i:reservationList){
            if(tablesList.contains(i.getTables_details()) && i.isReserved()==true){
                tablesList.remove(i.getTables_details());
            }
            if(tablesList.isEmpty()){
                break;
            }
        }
        if(tablesList.isEmpty()){
            return null;
        }
        else{
            Reservation reservation=new Reservation();
            reservation.setReservedDate(tableReserveDTO.getReserveDate());
            reservation.setReserved(true);
            reservation.setTables_details(tablesList.get(0));
            reservation.setUser(currentUser());
            Reservation res=reservationRepo.save(reservation);
            System.out.println("................................"+reservation);
            return  ReservationtoReservationDetails(res);
        }
    }

    public ReservationDetailsDTO cancelReservation(int id){
        Reservation reservation=reservationRepo.findById(id).get();
        reservation.setReserved(false);
        Reservation  res=reservationRepo.save(reservation);
        return ReservationtoReservationDetails(res);
    }

    private ReservationDetailsDTO ReservationtoReservationDetails(Reservation reservation){
        ReservationDetailsDTO reservationDetailsDTO=new ReservationDetailsDTO();
        reservationDetailsDTO.setReservationDate(reservation.getReservedDate());
        reservationDetailsDTO.setTableNo(reservation.getTables_details().getTableNo());
        reservationDetailsDTO.setCapacity(reservation.getTables_details().getCapacity());
        reservationDetailsDTO.setReservationId(reservation.getId());
        reservationDetailsDTO.setCharges(200 *(reservation.getTables_details().getCapacity()));
        reservationDetailsDTO.setStatus(reservation.isReserved());

        return reservationDetailsDTO;
    }
    private User currentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user=userRepo.findByUserName(username);
        return user;
    }
}
