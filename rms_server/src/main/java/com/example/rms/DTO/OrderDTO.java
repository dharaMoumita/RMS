package com.example.rms.DTO;

import java.util.Date;
import java.util.List;

public class OrderDTO {

    private int id;
    private double price;
    private String date;
    private boolean payment;
    private int userId;
    private List<OrderFoodDTO> orderFoodDTOList;

    public int getUserId() {
        return userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public List<OrderFoodDTO> getOrderFoodDTOList() {
        return orderFoodDTOList;
    }

    public void setOrderFoodDTOList(List<OrderFoodDTO> orderFoodDTOList) {
        this.orderFoodDTOList = orderFoodDTOList;
    }

    public boolean isPayment() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }
}
