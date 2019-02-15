package com.snoopgame.devices.objectsForJSON;


import java.util.ArrayList;

public class Orders {
    private ArrayList<Order>orders;

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public Orders(ArrayList<Order> orders) {

        this.orders = orders;
    }
}
