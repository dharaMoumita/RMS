package com.example.rms.Service.ServiceImpl;

import com.example.rms.DAO.FoodRepo;
import com.example.rms.DAO.OrderFoodRepo;
import com.example.rms.DAO.OrderRepo;
import com.example.rms.DAO.UserRepo;
import com.example.rms.DTO.OrderDTO;
import com.example.rms.DTO.OrderFoodDTO;
import com.example.rms.Entity.Auth.User;
import com.example.rms.Entity.Food_Order.FoodEntity;
import com.example.rms.Entity.Food_Order.OrderEntity;
import com.example.rms.Entity.Food_Order.OrderFoodEntity;
import com.example.rms.Service.OrderFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
@Service
public class OrderFoodServiceImpl implements OrderFoodService {


    @Autowired
    private FoodRepo foodRepo;

    @Autowired
    private OrderFoodRepo orderFoodRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public int addOrder(OrderDTO orderDTO) {

        User user=currentUser();
        System.out.println("................Service Entered");
        OrderEntity orderEntity=OrderDTOtoOrderEntity(orderDTO);
        System.out.println(orderEntity);
        List<OrderFoodEntity> orderFoodEntity=orderEntity.getOrders();
        orderEntity.setUser(user);
        orderFoodRepo.saveAll(orderFoodEntity);
        OrderEntity order=orderRepo.save(orderEntity);
        System.out.println(".....................................................Order Saved");
        System.out.println(orderEntity.toString());
        System.out.println("..................................................."+order);
        System.out.println("...................................................."+order.getOrders());
        return order.getId();
    }

    @Override
    public OrderDTO orderDetails(int id) {
        OrderEntity order=orderRepo.findById(id).get();
        return OrderEntitytoOrderDTO(order);
    }

    @Override
    public List<OrderDTO> allOrderByUserId() {
        List<OrderEntity> orderEntityList=orderRepo.findByUserId(currentUser().getId());
        List<OrderDTO> orderDTOList=new ArrayList<>();
        orderEntityList.forEach((ord)->{
            orderDTOList.add(OrderEntitytoOrderDTO(ord));
        });
        return orderDTOList;
    }

    private OrderDTO OrderEntitytoOrderDTO(OrderEntity orderEntity){
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setId(orderEntity.getId());
        orderDTO.setPrice(orderEntity.getTotal());
        orderDTO.setPayment(orderEntity.isPayment());
        orderDTO.setDate(orderEntity.getDate().toString());
        List<OrderFoodEntity> orderFoodEntityList=orderEntity.getOrders();
        List<OrderFoodDTO> orderFoodDTOList=new ArrayList<>();
        for(OrderFoodEntity i:orderFoodEntityList){
            OrderFoodDTO orderFoodDTO=new OrderFoodDTO();
            orderFoodDTO.setQuantity(i.getQuantity());
            orderFoodDTO.setFood_id(i.getFood_details().getId());
            orderFoodDTOList.add(orderFoodDTO);
        }
        orderDTO.setOrderFoodDTOList(orderFoodDTOList);
        orderDTO.setUserId(orderEntity.getUser().getId());
        return orderDTO;
    }


    private OrderEntity OrderDTOtoOrderEntity(OrderDTO orderDTO){
        double price=0.0;
        OrderEntity orderEntity=new OrderEntity();
//        OrderFoodEntity orderFoodEntity=new OrderFoodEntity();
        List<OrderFoodDTO> orderFoodDTOList=orderDTO.getOrderFoodDTOList();
        List<OrderFoodEntity> orderFoodEntityList=new ArrayList<>();
        for(OrderFoodDTO i:orderFoodDTOList){
            OrderFoodEntity orderFoodEntity=new OrderFoodEntity();
            FoodEntity food=foodRepo.findById(i.getFood_id()).get();
            int quantity=i.getQuantity();
            price+= food.getPrice()*quantity;
            orderFoodEntity.setFood_details(food);
           orderFoodEntity.setQuantity(quantity);
           orderFoodEntity.setOrder_details(orderEntity);
            orderFoodEntityList.add(orderFoodEntity);
//            orderFoodRepo.save(orderFoodEntity);
        }
        System.out.println("......................................"+price);
        orderEntity.setOrders(orderFoodEntityList);
try {
    orderEntity.setDate(new SimpleDateFormat("yyyy/MM/dd").parse(orderDTO.getDate()));
}
catch (Exception e){
    System.out.println(".................................................................Date Exception");
}
        orderEntity.setPayment(orderDTO.isPayment());
        orderEntity.setTotal(price);
        return orderEntity;

    }

    private User currentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user=userRepo.findByUserName(username);
        return user;
    }


}
