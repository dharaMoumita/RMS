package com.example.rms.Entity.Food_Order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="Order_Food")
public class OrderFoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="quantity")
    private int quantity;
    @Column (name="subtotal")
    private double subtotal;
//    @ManyToOne
//    private OrderEntity order_details;
//
//    @ManyToOne
//
//    private FoodEntity food_details;

//    @ManyToOne
//    private OrderEntity order;
//    @ManyToOne
//    private FoodEntity food;

    @JsonIgnore
        @ManyToOne(cascade = CascadeType.ALL)
    private OrderEntity order_details;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private FoodEntity food_details;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public OrderEntity getOrder_details() {
        return order_details;
    }

    public void setOrder_details(OrderEntity order_details) {
        this.order_details = order_details;
    }

    public FoodEntity getFood_details() {
        return food_details;
    }

    public void setFood_details(FoodEntity food_details) {
        this.food_details = food_details;
    }

    @Override
    public String toString() {
        return "OrderFoodEntity{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                '}';
    }
}






