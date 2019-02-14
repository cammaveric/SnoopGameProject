package com.snoopgame.devices.objectsForJSON;

import java.sql.Date;

public class Order {
    private int id;
    private Employee employee;

    private Phone phone;

    private Date date_start;/*Нужно тестить какой класс*/
    private Date date_end;
    private String status;
    public int getId() {
        return id;
    }

    public Order(int id, Employee employee, Phone phone, Date date_start, Date date_end, String status) {
        this.id = id;
        this.employee = employee;
        this.phone = phone;
        this.date_start = date_start;
        this.date_end = date_end;
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Date getDate_start() {
        return date_start;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

