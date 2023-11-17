package com.example.rms.DTO;

import java.sql.Date;
import java.util.List;

public class OrderDTO {

    private int id;
    private double price;
    private Date date;
    private boolean payment;
    private int userId;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    private int customerId;
    private List<OrderFoodDTO> orderFoodDTOList;

    public int getUserId() {
        return userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
