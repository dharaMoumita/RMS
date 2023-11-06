package com.example.rms.DTO;

public class TablesDTO {

    private int id;
    private int tableNo;
    private int capacity;

    public TablesDTO() {
    }

    public TablesDTO(int tableNo, int capacity, boolean isAvailable) {
        this.tableNo = tableNo;
        this.capacity = capacity;
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



    @Override
    public String toString() {
        return "TablesDTO{" +
                "id=" + id +
                ", tableNo=" + tableNo +
                ", capacity=" + capacity +
                '}';
    }
}
