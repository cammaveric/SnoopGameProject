package com.snoopgame.devices.objectsForJSON;


import java.util.ArrayList;

public class Phones {
    ArrayList<Phone> phones;
    public Phones(ArrayList<Phone> phones) {
        this.phones = phones;
    }

    public ArrayList<Phone> getPhones() {
        return phones;
    }

    public void setPhones(ArrayList<Phone> phones) {
        this.phones = phones;
    }
    public Phones(){}
}

