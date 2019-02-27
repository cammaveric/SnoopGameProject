package com.snoopgame.devices.objectsForJSON;


import java.util.ArrayList;

public class Phones {
    private ArrayList<Phone> androidPhones;
    private ArrayList<Phone> iOSPhones;
    private ArrayList<Phone> amazonPhones;


    public ArrayList<Phone> getAndroidPhones() {
        return androidPhones;
    }

    public void setAndroidPhones(ArrayList<Phone> androidPhones) {
        this.androidPhones = androidPhones;
    }

    public ArrayList<Phone> getiOSPhones() {
        return iOSPhones;
    }

    public void setiOSPhones(ArrayList<Phone> iOSPhones) {
        this.iOSPhones = iOSPhones;
    }

    public ArrayList<Phone> getAmazonPhones() {
        return amazonPhones;
    }

    public void setAmazonPhones(ArrayList<Phone> amazonPhones) {
        this.amazonPhones = amazonPhones;
    }

    public Phones(ArrayList<Phone> androidPhones, ArrayList<Phone> iOSPhones, ArrayList<Phone> amazonPhones) {

        this.androidPhones = androidPhones;
        this.iOSPhones = iOSPhones;
        this.amazonPhones = amazonPhones;
    }


}

