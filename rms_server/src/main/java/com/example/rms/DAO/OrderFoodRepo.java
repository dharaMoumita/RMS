package com.example.rms.DAO;

import com.example.rms.Entity.Food_Order.OrderFoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderFoodRepo extends JpaRepository<OrderFoodEntity,Integer> {
}
