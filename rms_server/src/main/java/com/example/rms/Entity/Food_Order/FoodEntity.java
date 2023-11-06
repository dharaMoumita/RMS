package com.example.rms.Entity.Food_Order;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Food_Table")
public class FoodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name="food_name")
    private String name;
    @Column(name="food_desc")
    private String desc;
    @Column(name="food_price")
    private double price;
    @Column(name="food_prep")
    private int minutes;

//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "food_details")
//  private List<OrderFoodEntity> foods = new ArrayList<>();

//    @ManyToMany(mappedBy = "orders")
//    private List<OrderEntity> foods=new ArrayList<>();


    @OneToMany(mappedBy = "food_details",cascade = CascadeType.ALL)
    private List<OrderFoodEntity> foods;

    public FoodEntity() {
    }

    public FoodEntity(String name, String desc, double price, int minutes) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.minutes = minutes;
    }

    public FoodEntity(int id, String name, String desc, double price, int minutes) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.minutes = minutes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public List<OrderFoodEntity> getFoods() {
        return foods;
    }

    public void setFoods(List<OrderFoodEntity> foods) {
        this.foods = foods;
    }

    @Override
    public String toString() {
        return "FoodEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", price=" + price +
                ", minutes=" + minutes +
                '}';
    }
}
