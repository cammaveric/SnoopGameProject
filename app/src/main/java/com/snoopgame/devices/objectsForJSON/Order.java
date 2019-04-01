package com.snoopgame.devices.objectsForJSON;

import java.sql.Timestamp;
import java.util.Set;


public class Order {
    private int id;
    private String date_start;
    private Timestamp date_end;
    private Employee employee;
    private Phone phone;
    private Status status;

    public Order() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public void setDate_end(Timestamp date_end) {
        this.date_end = date_end;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {

        return id;
    }

    public String getDate_start() {
        return date_start;
    }

    public Timestamp getDate_end() {
        return date_end;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Phone getPhone() {
        return phone;
    }

    public Status getStatus() {
        return status;
    }

    public Order(int id, String date_start, Timestamp date_end, Employee employee, Phone phone, Status status) {

        this.id = id;
        this.date_start = date_start;
        this.date_end = date_end;
        this.employee = employee;
        this.phone = phone;
        this.status = status;
    }
}

