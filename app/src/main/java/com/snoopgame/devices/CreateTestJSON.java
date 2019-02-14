package com.snoopgame.devices;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.snoopgame.devices.objectsForJSON.Employee;
import com.snoopgame.devices.objectsForJSON.Order;
import com.snoopgame.devices.objectsForJSON.Orders;
import com.snoopgame.devices.objectsForJSON.Phone;

import java.sql.Date;
import java.util.ArrayList;

public class CreateTestJSON {
    public void createJSON(){
        ArrayList <Order> a=new ArrayList<>();
        Employee employee=new Employee(1,"'jora","asd","asdsd");
        Phone phone=new Phone(1,"Samsung");
        Date date_s=new Date(1000);
        date_s.setTime(1000000);
        Date date_e=new Date(10000000);
        a.add(new Order(1, employee,phone,date_s,date_e,"executed"));
        Orders orders = new Orders(a);
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        String json=gson.toJson(orders);
        Log.i("json",json);
    }

}
