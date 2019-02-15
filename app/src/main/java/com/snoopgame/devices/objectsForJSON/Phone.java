package com.snoopgame.devices.objectsForJSON;

public class Phone {
    private int id;
    private String name;
    private String firmware;
    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Phone(int id, String name, String firmware, int amount) {
        this.id = id;
        this.name = name;
        this.firmware = firmware;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirmware() {
        return firmware;
    }

    public void setFirmware(String firmware) {
        this.firmware = firmware;
    }
}
