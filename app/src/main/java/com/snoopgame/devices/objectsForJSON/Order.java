package com.snoopgame.devices.objectsForJSON;

import java.sql.Date;
import java.util.Set;


public class Order {
    private long id;

    private String  date_start;/*SQL Date type cant parse*/
    private String  date_end;

    private Employee employee;

    private Phone phone;

    private Set<Status> statuses;

    public Order() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public String getDate_end() {
        return date_end;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
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

    public Set<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(Set<Status> statuses) {
        this.statuses = statuses;
    }

    public Order(long id, String date_start, String date_end, Employee employee, Phone phone, Set<Status> statuses) {

        this.id = id;
        this.date_start = date_start;
        this.date_end = date_end;
        this.employee = employee;
        this.phone = phone;
        this.statuses = statuses;
    }
}

