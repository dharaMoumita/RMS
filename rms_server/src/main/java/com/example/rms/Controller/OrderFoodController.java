package com.example.rms.Controller;

import com.example.rms.DTO.OrderDTO;
import com.example.rms.Service.OrderFoodService;
import org.springframework.aop.target.LazyInitTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
@CrossOrigin
@PreAuthorize("hasRole('Waiter')")
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

    @GetMapping("/orderFood/payment/{id}")
    public void setPayment(@PathVariable int id){
        orderFoodService.confirmPayment(id);
    }

    @GetMapping("/orderFood/date")
    public List<OrderDTO> getOrderByDate(){
       return orderFoodService.getallOrderByDateUser();
    }


    @PutMapping("/orderFood")
    public int updateOrder(@RequestBody OrderDTO orderDTO){
        return orderFoodService.updateOrder(orderDTO);
    }

    @GetMapping("/orderFood/dateWise/{date}")
    public List<OrderDTO> getOrderDateWise(@PathVariable String date){
        return orderFoodService.getOrderDateWise(date);
    }
}
