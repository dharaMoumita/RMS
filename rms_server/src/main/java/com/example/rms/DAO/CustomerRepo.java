package com.example.rms.DAO;

import com.example.rms.Entity.Auth.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {
}
