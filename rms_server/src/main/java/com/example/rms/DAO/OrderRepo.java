package com.example.rms.DAO;

import com.example.rms.Entity.Food_Order.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface OrderRepo extends JpaRepository<OrderEntity,Integer> {
    public List<OrderEntity> findByUserId(int userId);
    @Query("SELECT o FROM OrderEntity o WHERE o.date = ?1")
    List<OrderEntity> findAllOrdersByDate(Date date);
}
