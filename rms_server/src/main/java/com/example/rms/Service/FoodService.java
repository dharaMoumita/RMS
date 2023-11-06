package com.example.rms.Service;

import com.example.rms.DTO.FoodDTO;

import java.util.List;

public interface FoodService {
    public FoodDTO addFood (FoodDTO foodDTO);
    public FoodDTO deleteFood(int id);
    public FoodDTO updateFood(FoodDTO foodDTO,int id);
    public FoodDTO getFood(int id);
    public List<FoodDTO> getAllFood();




    }
