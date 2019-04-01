package com.snoopgame.devices.objectsForJSON;


import java.util.ArrayList;

public class Orders {
    private ArrayList<Order> androidOrders;
    private ArrayList<Order> iosOrders;
    private ArrayList<Order> amazonOrders;

    public Orders(ArrayList<Order> androidOrders, ArrayList<Order> iosOrders, ArrayList<Order> amazonOrders) {

        this.androidOrders = androidOrders;
        this.iosOrders = iosOrders;
        this.amazonOrders = amazonOrders;
    }

    public ArrayList<Order> getAndroidOrders() {
        return androidOrders;
    }

    public ArrayList<Order> getIosOrders() {
        return iosOrders;
    }

    public ArrayList<Order> getAmazonOrders() {
        return amazonOrders;
    }

    public void setAndroidOrders(ArrayList<Order> androidOrders) {
        this.androidOrders = androidOrders;
    }

    public void setIosOrders(ArrayList<Order> iosOrders) {
        this.iosOrders = iosOrders;
    }

    public void setAmazonOrders(ArrayList<Order> amazonOrders) {
        this.amazonOrders = amazonOrders;
    }

    public Orders() {

    }
}
