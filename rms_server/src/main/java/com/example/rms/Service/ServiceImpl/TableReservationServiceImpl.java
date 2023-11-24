package com.example.rms.Service.ServiceImpl;

import com.example.rms.DAO.CustomerRepo;
import com.example.rms.DAO.ReservationRepo;
import com.example.rms.DAO.TableRepo;
import com.example.rms.DAO.UserRepo;
import com.example.rms.DTO.ReservationDetailsDTO;
import com.example.rms.DTO.TableReserveDTO;
import com.example.rms.Entity.Auth.User;
import com.example.rms.Entity.Table_Reservation.Reservation;
import com.example.rms.Entity.Table_Reservation.Tables;
import com.example.rms.Service.TableReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class TableReservationServiceImpl implements TableReservationService {


    @Autowired
    private ReservationRepo reservationRepo;

    @Autowired
    private TableRepo tableRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CustomerRepo customerRepo;

//    @Override
//    public boolean tableAvailable(int id) {
//        return false;
//    }

//    @Override
//    public int reserveTableById(int tableId,int custId) throws Exception {
//        Tables tables=tableRepo.findById(tableId).get();
//        Reservation reservation=new Reservation();
//        reservation.setUser(currentUser());
//        reservation.setReservedDate(new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString()));
//        reservation.setTables_details(tables);
//        reservation.setCustomer_details(customerRepo.findById(custId).get());
//        reservation.setReserved(true);
//        Reservation res=reservationRepo.save(reservation);
//        return res.getId();
//    }

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
//    public List<Tables> tablesAvailable(int capacity,Date date){
//        List<Reservation> reservationList=reservationRepo.findReservedDate(date);
//        List<Tables> tablesList=tableRepo.findALlTablesByCapacity(capacity);
//        for(Reservation i:reservationList){
//            if(tablesList.contains(i.getTables_details()) && i.isReserved()==true){
//                tablesList.remove(i.getTables_details());
//            }
//            if(tablesList.isEmpty()){
//                break;
//            }
//        }
//        return tablesList;
//    }

//    public List<Tables> tablesAvailable(int capacity,Date date){
//        List<Reservation> reservationList=reservationRepo.findReservedDate(date);
//        List<Tables> tablesList=tableRepo.findALlTablesByCapacity(capacity);
//        for(Reservation i:reservationList){
//            if(tablesList.contains(i.getTables_details()) && i.isReserved()==true){
//                tablesList.remove(i.getTables_details());
//            }
//            if(tablesList.isEmpty()){
//                break;
//            }
//        }
//        return tablesList;
//    }





    public ReservationDetailsDTO makeReservation(TableReserveDTO tableReserveDTO){
        List<Reservation> reservationList=reservationRepo.findReservedDate(tableReserveDTO.getReserveDate());
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
            reservation.setReservedDate(Date.valueOf(tableReserveDTO.getReserveDate().toString()));
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

    @Override
    public List<Tables> getByCapacity(int capacity) {
        return tableRepo.findALlTablesByCapacity(capacity);
    }

    public List<ReservationDetailsDTO> findByDate(Date date){
        List<ReservationDetailsDTO> reservedtablesSet=new ArrayList<>();
        List<Reservation> reservationList=reservationRepo.findReservedDate(date);
            reservationList.forEach((item)->{
                    reservedtablesSet.add(ReservationtoReservationDetails(item));
            });
//        System.out.println(availbleTables);

        return reservedtablesSet;

    }

    @Override
    public List<Tables> tablesAvailable(TableReserveDTO tableReserveDTO) {
        Date date=Date.valueOf(tableReserveDTO.getReserveDate().toString());
        int capacity=tableReserveDTO.getReqCapcity();
        List<Tables> tablesList;
        if(date==null && capacity!=0){
            tablesList=tableRepo.findALlTablesByCapacity(capacity);
        }else if(capacity==0 && date!=null){
            List<Reservation> reservationList=reservationRepo.findReservedDate(date);
            tablesList=tableRepo.findAll();
            for(Reservation i:reservationList){
                if(tablesList.contains(i.getTables_details()) && i.isReserved()==true){
                    tablesList.remove(i.getTables_details());
                }
                if(tablesList.isEmpty()){
                    break;
                }
            }
        }else {
            List<Reservation> reservationList=reservationRepo.findReservedDate(date);
            tablesList=tableRepo.findALlTablesByCapacity(capacity);
            for(Reservation i:reservationList){
                if(tablesList.contains(i.getTables_details()) && i.isReserved()==true){
                    tablesList.remove(i.getTables_details());
                }
                if(tablesList.isEmpty()){
                    break;
                }
            }

        }



        return tablesList;    }

    @Override
    public int reserveTableById(int id, TableReserveDTO tableReserveDTO)throws Exception {
        Tables tables=tableRepo.findById(id).get();
        Reservation reservation=new Reservation();
        reservation.setUser(currentUser());
//        reservation.setReservedDate(new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString()));
        reservation.setReservedDate(Date.valueOf(tableReserveDTO.getReserveDate().toString()));
        reservation.setTables_details(tables);
        reservation.setCustomer_details(customerRepo.findById(tableReserveDTO.getCustomerId()).get());
        reservation.setReserved(true);
        Reservation res=reservationRepo.save(reservation);
        return res.getId();


    }

    @Override
    public List<ReservationDetailsDTO> getReservationByUser() {
        List<ReservationDetailsDTO> reservationList=new ArrayList<>();
        List<Reservation> reservations=reservationRepo.findAll();
        reservations.forEach(item->{
            if(item.getUser()==currentUser()){
                reservationList.add(ReservationtoReservationDetails(item));
            }
        });
        return  reservationList;
    }

    @Override
    public List<ReservationDetailsDTO> getReservationByDateUser() {
        List<ReservationDetailsDTO> reservationDetailsDTOList=new ArrayList<>();
        long millis=System.currentTimeMillis();
        Date date=new Date(millis);

       List<Reservation> reservationList= reservationRepo.findReservedDate(date);
       reservationList.forEach(ele->{
           if(ele.getUser()==currentUser()) {
               reservationDetailsDTOList.add(ReservationtoReservationDetails(ele));
           }
       });
       return reservationDetailsDTOList;
    }

    @Override
    public List<Tables> tablesAvailable(String reqCapacity, String reservedDate) throws Exception{
        Date date=null;
        int capacity=0;
        if(!reservedDate.equals("undefined")){
             date=Date.valueOf(reservedDate);
            System.out.println(date);
        }
        if(!reqCapacity.equals("undefined") || reqCapacity!=null){
            capacity=Integer.parseInt(reqCapacity);
        }

        System.out.println(date);
        List<Tables> tablesList;
        if(date==null && capacity!=0){
            System.out.println(date);
            System.out.println(capacity);
            tablesList=tableRepo.findALlTablesByCapacity(capacity);
        }else if(capacity==0 && date!=null){
            System.out.println(date);
            System.out.println(capacity);
            List<Reservation> reservationList=reservationRepo.findReservedDate(date);
            tablesList=tableRepo.findAll();
            for(Reservation i:reservationList){
                if(tablesList.contains(i.getTables_details()) && i.isReserved()==true){
                    tablesList.remove(i.getTables_details());
                }
                if(tablesList.isEmpty()){
                    break;
                }
            }
        }else {
            System.out.println(date);
            System.out.println(capacity);
            List<Reservation> reservationList=reservationRepo.findReservedDate(date);
            tablesList=tableRepo.findALlTablesByCapacity(capacity);
            for(Reservation i:reservationList){
                if(tablesList.contains(i.getTables_details()) && i.isReserved()==true){
                    tablesList.remove(i.getTables_details());
                }
                if(tablesList.isEmpty()){
                    break;
                }
            }
            System.out.println(tablesList);

        }



        return tablesList;
    }
    

    public ReservationDetailsDTO getByReservationId(int id){
        Reservation reservation=reservationRepo.findById(id).get();
        return ReservationtoReservationDetails(reservation);
    }


    private ReservationDetailsDTO ReservationtoReservationDetails(Reservation reservation){
        ReservationDetailsDTO reservationDetailsDTO=new ReservationDetailsDTO();
        reservationDetailsDTO.setReservationDate(reservation.getReservedDate());
        reservationDetailsDTO.setTableNo(reservation.getTables_details().getTableNo());
        reservationDetailsDTO.setCapacity(reservation.getTables_details().getCapacity());
        reservationDetailsDTO.setReservationId(reservation.getId());
        reservationDetailsDTO.setCharges(200 *(reservation.getTables_details().getCapacity()));
        reservationDetailsDTO.setStatus(reservation.isReserved());
        reservationDetailsDTO.setCustomerId(reservation.getCustomer_details().getId());
        reservationDetailsDTO.setUserId(reservation.getUser().getId());
        return reservationDetailsDTO;
    }
    private User currentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user=userRepo.findByUserName(username);
        return user;
    }
}
