package com.example.rms.Controller;

import com.example.rms.DTO.OrderDTO;
import com.example.rms.Service.OrderFoodService;
import org.springframework.aop.target.LazyInitTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
@CrossOrigin
public class OrderFoodController {

    @Autowired
    private OrderFoodService orderFoodService;

    @PostMapping("/orderFood")
    public int orderFood(@RequestBody OrderDTO orderDTO){
       return  orderFoodService.addOrder(orderDTO);
    }

    @GetMapping("/orderFood/{id}")
    public OrderDTO getOrder(@PathVariable int id){
        return orderFoodService.orderDetails(id);
    }

    @GetMapping("/orderFood/user")
    public List<OrderDTO> ordersByUserId(){
        return orderFoodService.allOrderByUserId();
    }

}
