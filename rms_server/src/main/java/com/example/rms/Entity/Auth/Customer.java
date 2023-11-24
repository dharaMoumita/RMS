package com.example.rms.Entity.Auth;


import com.example.rms.Entity.Food_Order.OrderEntity;
import com.example.rms.Entity.Table_Reservation.Reservation;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
@Entity
@Table(name = "customer_table",uniqueConstraints = { @UniqueConstraint(columnNames = { "customer_phone" }) })
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name="email")
    private String email;
    @Column(name = "customer_name")

    private String customerName;
    @Column(name="customer_phone")
    private String Phone;
    @Column(name = "birth_date")
    private Date birthdate;

    @OneToMany(mappedBy = "customer_details")
    List<Reservation> customer_reserve=new ArrayList<>();

    @OneToMany(mappedBy = "customer_order")
    List<OrderEntity> customerOrdersList=new ArrayList<>();

    public Customer() {
    }

//    public Customer(int id, String customerName, String phone, Date birthdate, List<Reservation> customer_reserve) {
//        this.id = id;
//        this.customerName = customerName;
//        Phone = phone;
//        this.birthdate = birthdate;
//        this.customer_reserve = customer_reserve;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

//    public List<Reservation> getCustomer_reserve() {
//        return customer_reserve;
//    }
//
//    public void setCustomer_reserve(List<Reservation> customer_reserve) {
//        this.customer_reserve = customer_reserve;
//    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", Phone='" + Phone + '\'' +
                ", birthdate=" + birthdate +
//                ", customer_reserve=" + customer_reserve +
                '}';
    }
}
