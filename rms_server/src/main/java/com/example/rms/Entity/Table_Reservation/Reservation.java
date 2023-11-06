package com.example.rms.Entity.Table_Reservation;

import com.example.rms.Entity.Auth.Customer;
import com.example.rms.Entity.Auth.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Table(name="Reservation_Table")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="is_Reserved")
    @Value("${isReserved.name:false}")
    private boolean isReserved;
    @Column(name = "Date_reserved")
    private Date reservedDate;


    @ManyToOne
    @JoinColumn(name = "table_id")
    private Tables tables_details;

    @ManyToOne
    private Customer customer_details;

    @ManyToOne
    private User user;

    public Customer getCustomer_details() {
        return customer_details;
    }

    public void setCustomer_details(Customer customer_details) {
        this.customer_details = customer_details;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Reservation() {
    }

    public Reservation(int id, boolean isReserved, Date reservedDate) {
        this.id = id;
        this.isReserved = isReserved;
        this.reservedDate = reservedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public Date getReservedDate() {
        return reservedDate;
    }

    public void setReservedDate(Date reservedDate) {
        this.reservedDate = reservedDate;
    }

    public Tables getTables_details() {
        return tables_details;
    }

    public void setTables_details(Tables tables_details) {
        this.tables_details = tables_details;
    }

//    public Customer getCustomer_details() {
//        return customer_details;
//    }
//
//    public void setCustomer_details(Customer customer_details) {
//        this.customer_details = customer_details;
//    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", isReserved=" + isReserved +
                ", reservedDate=" + reservedDate +
                '}';
    }
}
