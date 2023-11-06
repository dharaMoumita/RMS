package com.example.rms.DTO;

import java.util.Date;

public class TableReserveDTO {

    private Date reserveDate;
    private int reqCapcity;

    public Date getReserveDate() {
        return reserveDate;
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
