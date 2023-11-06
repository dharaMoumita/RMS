package com.example.rms.Entity.Food_Order;

import com.example.rms.Entity.Auth.Customer;
import com.example.rms.Entity.Auth.User;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name="Order_Table")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "order_date")
    private Date date;
    @Column(name = "order_total")
    private double total;
    @Column(name = "order_payment")
    private boolean payment;

//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "order_details")
//    private List<OrderFoodEntity> orders = new ArrayList<>();


//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "Order_Food", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "food_id"))
//    private List<FoodEntity> orders;


    @ManyToOne
    private User user;

    public Customer getCustomer_order() {
        return customer_order;
    }

    public void setCustomer_order(Customer customer_order) {
        this.customer_order = customer_order;
    }

    @OneToMany(mappedBy = "order_details",cascade = CascadeType.ALL)
    private List<OrderFoodEntity> orders;

    @ManyToOne
    private Customer customer_order;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isPayment() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }

    public List<OrderFoodEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderFoodEntity> orders) {
        this.orders = orders;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", date=" + date +
                ", total=" + total +
                ", payment=" + payment +
                '}';
    }
}
