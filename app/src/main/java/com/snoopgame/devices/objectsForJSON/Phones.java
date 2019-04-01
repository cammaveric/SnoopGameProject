package com.snoopgame.devices.objectsForJSON;


import java.util.ArrayList;

public class Phones {
    private ArrayList<Phone> androidPhones;
    private ArrayList<Phone> iosPhones;
    private ArrayList<Phone> amazonPhones;


    public Phones(ArrayList<Phone> androidPhones, ArrayList<Phone> iosPhones, ArrayList<Phone> amazonPhones) {

        this.androidPhones = androidPhones;
        this.iosPhones = iosPhones;
        this.amazonPhones = amazonPhones;
    }

    public ArrayList<Phone> getAndroidPhones() {
        return androidPhones;
    }

    public ArrayList<Phone> getIosPhones() {
        return iosPhones;
    }

    public void setAndroidPhones(ArrayList<Phone> androidPhones) {
        this.androidPhones = androidPhones;
    }

    public void setiosPhones(ArrayList<Phone> iosPhones) {
        this.iosPhones = iosPhones;
    }

    public void setAmazonPhones(ArrayList<Phone> amazonPhones) {
        this.amazonPhones = amazonPhones;
    }

    public Phones() {

    }

    public ArrayList<Phone> getAmazonPhones() {
        return amazonPhones;
    }


}

