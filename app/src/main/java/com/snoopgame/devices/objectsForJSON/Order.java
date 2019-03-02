package com.snoopgame.devices.objectsForJSON;

import java.sql.Timestamp;
import java.util.Set;


public class Order {
    private int id;
    private String date_start;
    private Timestamp date_end;
    private Employee employee;
    private Phone phone;
    private Set<Status> statuses;

    public Order() {
    }

    public Order(int id, String date_start, Timestamp date_end, Employee employee, Phone phone, Set<Status> statuses) {

        this.id = id;
        this.date_start = date_start;
        this.date_end = date_end;
        this.employee = employee;
        this.phone = phone;
        this.statuses = statuses;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_start() {
        return date_start;
    }


    public Employee getEmployee() {
        return employee;
    }


    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

}

