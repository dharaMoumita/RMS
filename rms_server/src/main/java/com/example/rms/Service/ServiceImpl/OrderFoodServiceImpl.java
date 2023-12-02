package com.example.rms.Service.ServiceImpl;

import com.example.rms.DAO.*;
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
import java.sql.Date;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public int addOrder(OrderDTO orderDTO) {

        User user=currentUser();
        System.out.println("................Service Entered");
        System.out.println(orderDTO.getDate());
        OrderEntity orderEntity=OrderDTOtoOrderEntity(orderDTO);
        System.out.println(orderEntity);
        List<OrderFoodEntity> orderFoodEntity=orderEntity.getOrders();
        orderEntity.setUser(user);
        orderEntity.setCustomer_order(customerRepo.findById(orderDTO.getCustomerId()).get());
        orderFoodRepo.saveAll(orderFoodEntity);
        OrderEntity order=orderRepo.save(orderEntity);
        System.out.println(".....................................................Order Saved");
        System.out.println(orderEntity.toString());
        System.out.println("..................................................."+order);
        System.out.println("...................................................."+order.getOrders());
        return order.getId();
    }


    public int updateOrder(OrderDTO orderDTO){
        OrderEntity orderEntity=orderRepo.findById(orderDTO.getId()).get();
        List<OrderFoodDTO> orderFoodDTOList=orderDTO.getOrderFoodDTOList();
        List<OrderFoodEntity> orderFoodEntityList=new ArrayList<>();
        double price=0.0;
         price=orderEntity.getTotal();
        List<OrderFoodEntity> orderFoodEntities=orderEntity.getOrders();
        if(!orderFoodEntities.isEmpty()){
       for (int k=0 ;k<orderFoodDTOList.size();k++){
           OrderFoodDTO i=orderFoodDTOList.get(k);
           for(int j=0;j<orderFoodEntities.size();j++){
               OrderFoodEntity ele=orderFoodEntities.get(j);
               if(ele.getFood_details().getId()==i.getFood_id()){
                   ele.setQuantity(ele.getQuantity()+i.getQuantity());
                   price+=ele.getFood_details().getPrice()*i.getQuantity();
                   System.out.println("***********************************"+i.getFood_id());
                   System.out.println("**************************"+ele.getFood_details().getId());
//                   orderFoodDTOList.remove(i);
                   orderFoodRepo.save(ele);


               }
           }
           System.out.println("************************************************************"+i.toString());
       }}
        System.out.println("\n"+price);

        List<OrderFoodDTO> filteredList = orderFoodDTOList.stream()
                .filter(empl -> orderFoodEntities.stream()
                        .allMatch(dept ->
                                !(dept.getFood_details().getId()== empl.getFood_id())))
                .collect(Collectors.toList());
        System.out.println(filteredList);
       if(!filteredList.isEmpty()) {
           for (OrderFoodDTO i : filteredList) {

               OrderFoodEntity orderFoodEntity = new OrderFoodEntity();
               FoodEntity food = foodRepo.findById(i.getFood_id()).get();
               int quantity = i.getQuantity();
               price += food.getPrice() * quantity;
               orderFoodEntity.setFood_details(food);
               orderFoodEntity.setQuantity(quantity);
               orderFoodEntity.setOrder_details(orderEntity);
               orderFoodEntityList.add(orderFoodEntity);
//            orderFoodRepo.save(orderFoodEntity);
           }
       }
        System.out.println(price);
        orderEntity.setTotal(price);
        orderEntity.setPayment(false);
        orderEntity.setOrders(orderFoodEntityList);
        OrderEntity order=orderRepo.save(orderEntity);
        System.out.println(order);
        System.out.println("........................................................");
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

    @Override
    public void confirmPayment(int id) {
        OrderEntity order=orderRepo.findById(id).get();
        order.setPayment(true);
        orderRepo.save(order);
    }

    @Override
    public List<OrderDTO> getallOrderByDateUser() {
        User currentUser=currentUser();
        long millis=System.currentTimeMillis();
        List<OrderDTO> orderDTOList=new ArrayList<>();
        Date today = new java.sql.Date(millis);
        System.out.println(today);
        List<OrderEntity> orderEntityList=orderRepo.findAllOrdersByDate(today);
        orderEntityList.forEach(ele->{
            if(ele.getUser()==currentUser){
            orderDTOList.add(OrderEntitytoOrderDTO(ele));}
        });

        return orderDTOList;
    }

    @Override
    public List<OrderDTO> getOrderDateWise(String date) {
        User currentUser=currentUser();

        List<OrderDTO> orderDTOList=new ArrayList<>();
        Date today = Date.valueOf(date);
        List<OrderEntity> orderEntityList=orderRepo.findAllOrdersByDate(today);
        orderEntityList.forEach(ele->{
            if(ele.getUser()==currentUser){
                orderDTOList.add(OrderEntitytoOrderDTO(ele));}
        });

        return orderDTOList;

    }

    private OrderDTO OrderEntitytoOrderDTO(OrderEntity orderEntity){
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setId(orderEntity.getId());
        orderDTO.setPrice(orderEntity.getTotal());
        orderDTO.setPayment(orderEntity.isPayment());
        orderDTO.setDate((orderEntity.getDate()));
        List<OrderFoodEntity> orderFoodEntityList=orderEntity.getOrders();
        List<OrderFoodDTO> orderFoodDTOList=new ArrayList<>();
        for(OrderFoodEntity i:orderFoodEntityList){
            OrderFoodDTO orderFoodDTO=new OrderFoodDTO();
            orderFoodDTO.setQuantity(i.getQuantity());
            orderFoodDTO.setFood_id(i.getFood_details().getId());
            orderFoodDTOList.add(orderFoodDTO);
        }
        orderDTO.setCustomerId(orderEntity.getCustomer_order().getId());
        orderDTO.setOrderFoodDTOList(orderFoodDTOList);
        orderDTO.setUserId(orderEntity.getUser().getId());
        return orderDTO;
    }


    private OrderEntity OrderDTOtoOrderEntity(OrderDTO orderDTO){
        double price=0.0;
        OrderEntity orderEntity=new OrderEntity();

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
    orderEntity.setDate(orderDTO.getDate());
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
