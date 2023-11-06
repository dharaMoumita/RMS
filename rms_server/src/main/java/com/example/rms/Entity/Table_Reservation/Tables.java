package com.example.rms.Entity.Table_Reservation;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name="Tables_table")
public class Tables {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Table_no",unique = true)
    private int tableNo;
    @Column(name = "capacity")
    private int capacity;
    @Column(name="is_available")
    private boolean isAvailable;


//    @OneToMany(mappedBy ="tables_details" )
//    List<Reservation> table_reserve =new ArrayList<>();

    public Tables() {
    }

    public Tables(int tableNo, int capacity, boolean isAvailable) {
        this.tableNo = tableNo;
        this.capacity = capacity;
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTableNo() {
        return tableNo;
    }

    public void setTableNo(int tableNo) {
        this.tableNo = tableNo;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

//    public List<Reservation> getTable_reserve() {
//        return table_reserve;
//    }
//
//    public void setTable_reserve(List<Reservation> table_reserve) {
//        this.table_reserve = table_reserve;
//    }

    @Override
    public String toString() {
        return "Tables{" +
                "id=" + id +
                ", tableNo=" + tableNo +
                ", capacity=" + capacity +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
