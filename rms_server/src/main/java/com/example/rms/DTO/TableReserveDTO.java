package com.example.rms.DTO;

import java.sql.Date;

public class TableReserveDTO {

    private Date reserveDate;
    private int reqCapcity;
    private int customerId;

    public Date getReserveDate() {
        return reserveDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "TableReserveDTO{" +
                "reserveDate=" + reserveDate +
                ", reqCapcity=" + reqCapcity +
                ", customerId=" + customerId +
                '}';
    }

    public void setReserveDate(Date reserveDate) {
        this.reserveDate = reserveDate;
    }

    public int getReqCapcity() {
        return reqCapcity;
    }

    public void setReqCapcity(int reqCapcity) {
        this.reqCapcity = reqCapcity;
    }
}
