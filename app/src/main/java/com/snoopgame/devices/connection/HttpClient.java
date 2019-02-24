package com.snoopgame.devices.connection;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.snoopgame.devices.DashboardFragment;
import com.snoopgame.devices.PutDeviceFragment;
import com.snoopgame.devices.TakeDeviceFragment;
import com.snoopgame.devices.objectsForJSON.Employee;
import com.snoopgame.devices.objectsForJSON.Employees;
import com.snoopgame.devices.objectsForJSON.Order;
import com.snoopgame.devices.objectsForJSON.Orders;
import com.snoopgame.devices.objectsForJSON.Phone;
import com.snoopgame.devices.objectsForJSON.Phones;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpClient {
    private static final String IP="192.168.0.106";
    private static final String URL_ORDER = "http://"+IP+":8080/order/";
    private static final String URL_PHONE = "http://"+IP+":8080/phone/get";
    private static final String URL_EMPLOYEE = "http://"+IP+":8080/employee/get";
    private OkHttpClient client;
    private Request request;
    private String responseString;
    private Orders orders;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public HttpClient() {
        client = new OkHttpClient();
    }

    public void doGetRequestOrders(DashboardFragment dashboardFragment, PutDeviceFragment putDeviceFragment) {
        request = new Request.Builder()
                .url(URL_ORDER)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("connClose", Arrays.toString(e.getStackTrace()));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                        responseString = response.body().string();
                        orders = gson.fromJson(responseString, Orders.class);
                        Log.i("responseOrder",responseString);
                    if (dashboardFragment != null) {
                        if (dashboardFragment.getActivity() != null) {
                            dashboardFragment.getActivity().runOnUiThread(() -> {
                                for (int i = 0; i < orders.getOrders().size(); i++) {
                                    Order o=orders.getOrders().get(i);
                                    dashboardFragment.dash_components.add("Телефон: " + o.getPhone().getName() + "\n" +
                                            "Прошивка: " + o.getPhone().getFirmware() + "\n" +
                                            "Фамилия: " + o.getEmployee().getSurname() +"\n"+
                                            "Имя: "+ o.getEmployee().getName() + "\n"+
                                            "Отчество: " + o.getEmployee().getMiddleName() + "\n" +
                                            "Дата выдачи: " + o.getDate_start());
                                }
                                dashboardFragment.setListView();
                            });
                        }
                    } else {
                          if (putDeviceFragment.getActivity() != null) {
                            putDeviceFragment.getActivity().runOnUiThread(() -> {
                                putDeviceFragment.put_components=new String[orders.getOrders().size()];
                                for (int i = 0; i < orders.getOrders().size(); i++) {
                                    Order o=orders.getOrders().get(i);
                                    putDeviceFragment.put_components[i]="id: "+o.getId()+"\n"+"Телефон: " + o.getPhone().getName() + "\n" +
                                            "Прошивка: " + o.getPhone().getFirmware() + "\n" +
                                            "Фамилия: " + o.getEmployee().getSurname() +"\n"+
                                            "Имя: " + o.getEmployee().getName() +"\n"+
                                            "Отчество: " + o.getEmployee().getMiddleName() + "\n" +
                                            "Дата выдачи: " + o.getDate_start();
                                }
                                putDeviceFragment.setListView();
                            });
                        }

                    }
                }
            }
        });
    }

    public void doGetRequestPhone(DashboardFragment dashboardFragment,TakeDeviceFragment takeDeviceFragment) {
        request = new Request.Builder()
                .url(URL_PHONE)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("connClose", Arrays.toString(e.getStackTrace()));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseString = response.body().string();
                    Phones phones = gson.fromJson(responseString, Phones.class);
                    Log.i("responsePhone",responseString);
                    if (dashboardFragment != null) {
                        if (dashboardFragment.getActivity() != null) {
                            dashboardFragment.getActivity().runOnUiThread(() -> {
                                for (int i = 0; i < phones.getPhones().size(); i++) {
                                    Phone p = phones.getPhones().get(i);
                                    if (p.getFree_phone_amount() == 0) {
                                        continue;
                                    }
                                    dashboardFragment.dash_components.add("Name: " + p.getName() + "\n" +
                                            "Firmware: " + p.getFirmware() + "\n" +
                                            "Amount: " + p.getFree_phone_amount());
                                }
                            });
                            doGetRequestOrders(dashboardFragment,null);
                        }
                    } else {
                        if (takeDeviceFragment.getActivity() != null) {
                            takeDeviceFragment.getActivity().runOnUiThread(() -> {
                                takeDeviceFragment.phone_components = new String[phones.getPhones().size()];
                                for (int i = 0; i < phones.getPhones().size(); i++) {
                                    Phone p = phones.getPhones().get(i);
                                    if (p.getFree_phone_amount() == 0) {
                                        continue;
                                    }
                                    takeDeviceFragment.phone_components[i] = "ID: " + p.getId() + "\n" +
                                            "Name: " + p.getName() + "\n" +
                                            "Firmware: " + p.getFirmware() + "\n" +
                                            "Amount: " + p.getFree_phone_amount();
                                }
                                takeDeviceFragment.setPhoneListView();
                            });
                        }
                    }
                }
            }
        });
    }

    public void doGetRequestEmployee(TakeDeviceFragment takeDeviceFragment) {
        request = new Request.Builder()
                .url(URL_EMPLOYEE)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("connClose", Arrays.toString(e.getStackTrace()));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseString = response.body().string();
                Employees employees = gson.fromJson(responseString, Employees.class);
                if (takeDeviceFragment != null) {
                    takeDeviceFragment.getActivity().runOnUiThread(() -> {
                        takeDeviceFragment.employee_components = new String[employees.getEmployees().size()];
                        for (int i = 0; i < employees.getEmployees().size(); i++) {
                            Employee e = employees.getEmployees().get(i);
                            takeDeviceFragment.employee_components[i] = "ID: "+e.getId()+"\n"+
                                    "Surname: " + e.getSurname() +"\n"+
                                    "Name: " + e.getName() + "\n"+
                                    "Middle name: " + e.getMiddleName();
                        }
                        takeDeviceFragment.setEmployeeListView();
                    });
                }
            }
        });
    }

    public void doPostRequestOrder(String action,Order order) {

         String json = gson.toJson(order);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder()
                .url(URL_ORDER + action)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("connClose", Arrays.toString(e.getStackTrace()));
            }

            @Override
            public void onResponse(Call call, Response response) {

            }
        });
    }
}