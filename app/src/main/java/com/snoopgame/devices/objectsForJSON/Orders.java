package com.snoopgame.devices.objectsForJSON;


import java.util.ArrayList;

public class Orders {
    private ArrayList<Order>orders=new ArrayList<Order>();

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public Orders(ArrayList<Order> orders) {

        this.orders = orders;
    }
    public void addOrder(Order order){
        orders.add(order);
    }
    public void removeOrder(Order order){
        orders.remove(order);
    }
}
