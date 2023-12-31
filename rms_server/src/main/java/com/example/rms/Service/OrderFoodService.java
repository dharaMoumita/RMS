package com.example.rms.Service;

import com.example.rms.DTO.OrderDTO;

import java.util.List;

public interface OrderFoodService {

    public int addOrder(OrderDTO orderDTO);
    public OrderDTO orderDetails(int id);
    public List<OrderDTO> allOrderByUserId();

    int updateOrder(OrderDTO orderDto);
    public void confirmPayment(int id);

    List<OrderDTO> getallOrderByDateUser();

    List<OrderDTO> getOrderDateWise(String date);
}
