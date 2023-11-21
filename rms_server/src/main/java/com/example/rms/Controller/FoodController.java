package com.example.rms.Controller;

import com.example.rms.DTO.FoodDTO;
import com.example.rms.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.sql.Date;

@CrossOrigin
@RestController
@RequestMapping
@PreAuthorize("hasRole('Waiter')")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @PostMapping("/foods")
    public FoodDTO addFood(@RequestBody FoodDTO foodDTO) {
        return foodService.addFood(foodDTO);
    }

    @DeleteMapping("/foods/{id}")
    public FoodDTO deleteFood(@PathVariable int id) {
        return foodService.deleteFood(id);
    }

    @PutMapping("/foods/{id}")
    public FoodDTO updateFood(@RequestBody FoodDTO foodDTO, @PathVariable int id) {
        return foodService.updateFood(foodDTO, id);
    }

    @GetMapping("/foods/{id}")
    public FoodDTO getFood(@PathVariable int id) {
        return foodService.getFood(id);
    }

    @GetMapping("/foods")
    public List<FoodDTO> getAllFood() {
        return foodService.getAllFood();
    }
}

//    @Autowired
//    private FoodRepo foodRepo;
//    @Autowired
//    private OrderRepo orderRepo;
//    @Autowired
//    private OrderFoodRepo orderFoodRepo;



//    @PostMapping("/addOrder")
//    public String adddOrder(@RequestBody OrderDTO orderDTO){
//        OrderEntity order=new OrderEntity();
////        order.setDate(orderDTO.getDate());
//
//        FoodEntity food=foodRepo.findById(orderDTO.getOrderFoodDTOList().get(0).getFood_id()).get();
//        OrderFoodEntity orderFoodEntity=new OrderFoodEntity();
//        orderFoodEntity.setFood_details(food);
//        orderFoodEntity.setQuantity(orderDTO.getOrderFoodDTOList().get(0).getQuantity());
//        orderFoodEntity.setOrder_details(order);
//        List<OrderFoodEntity> orderFoodEntityList=new ArrayList<>();
//        orderFoodEntityList.add(orderFoodEntity);
////        order.setOrders(orderFoodEntityList);
////        orderFoodRepo.save(orderFoodEntity);
//        order.setPayment(false);
//        order.setTotal(500000);        orderFoodRepo.save(orderFoodEntity);
//
//        orderRepo.save(order);
//        System.out.println("....................................."+orderFoodEntity.getOrder_details().getId());
//        return "Orderees";
//    }





