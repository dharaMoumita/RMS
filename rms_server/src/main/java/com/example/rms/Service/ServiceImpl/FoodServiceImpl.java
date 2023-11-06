package com.example.rms.Service.ServiceImpl;

import com.example.rms.DTO.FoodDTO;
import com.example.rms.Entity.Food_Order.FoodEntity;
import com.example.rms.DAO.FoodRepo;
import com.example.rms.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepo foodRepo;

    @Override
    public FoodDTO addFood(FoodDTO foodDTO) {
        FoodEntity food=FoodDTOtoFood(foodDTO);
        foodRepo.save(food);
        return FoodEntitytoFoodDTO(food);
    }

    public FoodDTO deleteFood(int id){
        FoodEntity food=foodRepo.findById(id).get();
        FoodDTO foodDTO=FoodEntitytoFoodDTO(food);
        foodRepo.delete(food);
        return foodDTO;
    }

    public FoodDTO updateFood(FoodDTO foodDTO,int id){
        FoodEntity food=foodRepo.findById(id).get();
        FoodEntity foodEntity=FoodDTOtoFood(foodDTO);
        System.out.println(foodEntity);
        foodEntity.setId(id);
        foodRepo.save(foodEntity);
        return FoodEntitytoFoodDTO(foodEntity);

    }

    public FoodDTO getFood(int id){
        FoodEntity food=foodRepo.findById(id).get();
        return FoodEntitytoFoodDTO(food);
    }

    public List<FoodDTO> getAllFood(){
        List<FoodEntity> foodEntityList=foodRepo.findAll();
        List<FoodDTO> foodDTOList=new ArrayList<>();
        for(FoodEntity i:foodEntityList){
           foodDTOList.add(FoodEntitytoFoodDTO(i));
        }

        return foodDTOList;

    }

    private FoodEntity FoodDTOtoFood(FoodDTO foodDTO){
        FoodEntity food=new FoodEntity();
        food.setName(foodDTO.getName());
        food.setDesc(foodDTO.getDesc());
        food.setMinutes(foodDTO.getMinutes());
        food.setPrice(foodDTO.getPrice());
        return food;
    }
    private FoodDTO FoodEntitytoFoodDTO(FoodEntity food){
        FoodDTO foodDTO=new FoodDTO();
        foodDTO.setId(food.getId());
        foodDTO.setDesc(food.getDesc());
        foodDTO.setMinutes(food.getMinutes());
        foodDTO.setName(food.getName());
        foodDTO.setPrice(food.getPrice());
        return  foodDTO;
    }
}
