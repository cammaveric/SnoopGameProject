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

    private static final String URL_ORDER = "http://192.168.0.94:8080/order/";
    private static final String URL_PHONE = "http://192.168.0.94:8080/phone/";
    private static final String URL_EMPLOYEE = "http://192.168.0.94:8080/employee/";
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
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    try {
                        responseString = response.body().string();
                        orders = gson.fromJson(responseString, Orders.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (dashboardFragment != null) {
                        if (dashboardFragment.getActivity() != null) {
                            dashboardFragment.getActivity().runOnUiThread(() -> {
                                for (int i = 0; i < orders.getOrders().size(); i++) {
                                    Order o=orders.getOrders().get(i);
                                    dashboardFragment.dash_components.add("Телефон: " + o.getPhone().getName() + "\n" +
                                            "Прошивка: " + o.getPhone().getFirmware() + "\n" +
                                            "ФИО: " + o.getEmployee().getSurname() +
                                            " " + o.getEmployee().getName() +
                                            " " + o.getEmployee().getMiddleName() + "\n" +
                                            "Дата выдачи: " + o.getDate_start());
                                }
                            });
                            doGetRequestPhone(dashboardFragment,null);
                        }
                    } else {
                          if (putDeviceFragment.getActivity() != null) {
                            putDeviceFragment.getActivity().runOnUiThread(() -> {
                                putDeviceFragment.put_components=new String[orders.getOrders().size()];
                                putDeviceFragment.id_orders=new int[orders.getOrders().size()];
                                for (int i = 0; i < orders.getOrders().size(); i++) {
                                    Order o=orders.getOrders().get(i);
                                    putDeviceFragment.id_orders[i]=o.getId();
                                    putDeviceFragment.put_components[i]="Телефон: " + o.getPhone().getName() + "\n" +
                                            "Прошивка: " + o.getPhone().getFirmware() + "\n" +
                                            "ФИО: " + o.getEmployee().getSurname() +
                                            " " + o.getEmployee().getName() +
                                            " " + o.getEmployee().getMiddleName() + "\n" +
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
                String responseString = response.body().string();
                Phones phones = gson.fromJson(responseString, Phones.class);
                Log.i("response",responseString);
                if (dashboardFragment != null) {
                    if (dashboardFragment.getActivity() != null) {
                        dashboardFragment.getActivity().runOnUiThread(() -> {
                            for (int i = 0; i < phones.getPhones().size(); i++) {
                                Phone p = phones.getPhones().get(i);
                                if (p.getAmount() == 0) {
                                    continue;
                                }
                                dashboardFragment.dash_components.add("Name: " + p.getName() + "\n" +
                                        "Firmware: " + p.getFirmware() + "\n" +
                                        "Amount: " + p.getAmount());
                            }
                            dashboardFragment.setListView();
                        });
                    }
                } else {
                    if (takeDeviceFragment.getActivity() != null) {
                        takeDeviceFragment.getActivity().runOnUiThread(() -> {
                            takeDeviceFragment.phone_components=new String[phones.getPhones().size()];
                            takeDeviceFragment.phones_id=new int[phones.getPhones().size()];
                            for (int i = 0; i < phones.getPhones().size(); i++) {
                                Phone p = phones.getPhones().get(i);
                                if (p.getAmount() == 0) {
                                    continue;
                                }
                                takeDeviceFragment.phones_id[i]=p.getId();
                                takeDeviceFragment.phone_components[i]="Name: " + p.getName() + "\n" +
                                        "Firmware: " + p.getFirmware() + "\n" +
                                        "Amount: " + p.getAmount();
                            }
                            takeDeviceFragment.setPhoneListView();
                        });
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
                Log.i("response", responseString);
                if (takeDeviceFragment != null) {
                    takeDeviceFragment.getActivity().runOnUiThread(() -> {
                        takeDeviceFragment.employee_components = new String[employees.getEmployees().size()];
                        takeDeviceFragment.employees_id = new int[employees.getEmployees().size()];
                        for (int i = 0; i < employees.getEmployees().size(); i++) {
                            Employee e = employees.getEmployees().get(i);
                            takeDeviceFragment.employees_id[i] = e.getId();
                            takeDeviceFragment.employee_components[i] = "FIO: " + e.getSurname() +
                                    " " + e.getName() + " " + e.getMiddleName();
                        }
                        takeDeviceFragment.setEmployeeListView();
                    });
                }
            }
        });
    }

    public void doPostRequestPhone() {
    }

    public void doPostRequestEmployee() {
    }

    public void doPostRequestOrder(String action,Order order) {
        String json = gson.toJson(order);
        Log.i("json", json);
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
                if (response.isSuccessful()) {
                    try {
                        responseString = response.body().string();
                    } catch (IOException e) {
                        Log.e("connClose", Arrays.toString(e.getStackTrace()));
                    }
                    Log.i("response", responseString);
                }
            }
        });
    }
}