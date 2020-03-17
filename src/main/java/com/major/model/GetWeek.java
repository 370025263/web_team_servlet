package com.major.model;

import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class GetWeek {
    public GetWeek() {
    }

    int DateDiff(Date date1, Date date2) {
        int i = (int)((date1.getTime() - date2.getTime()) / 3600L / 24L / 1000L);
        return i;
    }

    public int getweek() {
        Date currentdate = new Date();
        currentdate.setTime(currentdate.getTime());
        Date enddate = new Date();
        enddate.setTime(1565622851000L);
        return this.DateDiff(currentdate, enddate) / 7;
    }
}
