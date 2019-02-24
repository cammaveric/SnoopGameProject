package com.snoopgame.devices.objectsForJSON;

public class Employee {
    private long id;
    private String name;
    private String surname;
    private String middleName;

    public Employee(long id, String name, String surname, String middleName) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
    }

    public Employee() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public long getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
