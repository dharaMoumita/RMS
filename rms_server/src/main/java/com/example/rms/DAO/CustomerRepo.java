package com.example.rms.DAO;

import com.example.rms.Entity.Auth.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer> {

    @Query("SELECT c FROM Customer c WHERE c.Phone = :phoneNumber")
    List<Customer> findByPhone(@Param("phoneNumber") String phoneNumber);}
