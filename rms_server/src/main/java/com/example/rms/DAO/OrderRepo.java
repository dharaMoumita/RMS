package com.example.rms.DAO;

import com.example.rms.Entity.Food_Order.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<OrderEntity,Integer> {
    public List<OrderEntity> findByUserId(int userId);
}
