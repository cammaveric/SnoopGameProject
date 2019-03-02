package com.snoopgame.devices.objectsForJSON;

public class Phone {

    private int id;
    private String name;
    private int amount;
    private int free_phone_amount;
    private String firmware_name;
    private String firmware_version;

    public Phone(int id, String name, int amount, int free_phone_amount, String firmware_name, String firmware_version) {

        this.id = id;
        this.name = name;
        this.amount = amount;
        this.free_phone_amount = free_phone_amount;
        this.firmware_name = firmware_name;
        this.firmware_version = firmware_version;
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

    public int getFree_phone_amount() {
        return free_phone_amount;
    }

    public String getFirmware_name() {
        return firmware_name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setFree_phone_amount(int free_phone_amount) {
        this.free_phone_amount = free_phone_amount;
    }

    public void setFirmware_name(String firmware_name) {
        this.firmware_name = firmware_name;
    }

    public void setFirmware_version(String firmware_version) {
        this.firmware_version = firmware_version;
    }

    public Phone() {

    }

    public String getFirmware_version() {
        return firmware_version;
    }
}
