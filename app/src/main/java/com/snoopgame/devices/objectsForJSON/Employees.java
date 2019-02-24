package com.snoopgame.devices.objectsForJSON;


import java.util.ArrayList;

public class Employees {
    ArrayList<Employee> employees;
    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public Employees(ArrayList<Employee> employees) {
        this.employees = employees;
    }
    public Employees(){}
}
