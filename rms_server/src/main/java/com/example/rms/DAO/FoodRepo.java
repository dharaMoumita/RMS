package com.example.rms.DAO;

import com.example.rms.Entity.Food_Order.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepo extends JpaRepository<FoodEntity,Integer> {
}
