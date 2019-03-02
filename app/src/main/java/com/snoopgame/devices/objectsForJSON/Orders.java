package com.snoopgame.devices.objectsForJSON;


import java.util.ArrayList;

public class Orders {
    private ArrayList<Order> androidOrders;
    private ArrayList<Order> iOSOrders;
    private ArrayList<Order> amazonOrders;

    public Orders(ArrayList<Order> androidOrders, ArrayList<Order> iOSOrders, ArrayList<Order> amazonOrders) {

        this.androidOrders = androidOrders;
        this.iOSOrders = iOSOrders;
        this.amazonOrders = amazonOrders;
    }

    public ArrayList<Order> getAndroidOrders() {
        return androidOrders;
    }

    public ArrayList<Order> getiOSOrders() {
        return iOSOrders;
    }

    public ArrayList<Order> getAmazonOrders() {
        return amazonOrders;
    }

    public void setAndroidOrders(ArrayList<Order> androidOrders) {
        this.androidOrders = androidOrders;
    }

    public void setiOSOrders(ArrayList<Order> iOSOrders) {
        this.iOSOrders = iOSOrders;
    }

    public void setAmazonOrders(ArrayList<Order> amazonOrders) {
        this.amazonOrders = amazonOrders;
    }

    public Orders() {

    }
}
