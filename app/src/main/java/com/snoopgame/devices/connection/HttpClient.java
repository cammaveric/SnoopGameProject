package com.snoopgame.devices.connection;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.snoopgame.devices.fragments.DashboardFragment;
import com.snoopgame.devices.fragments.PutDeviceFragment;
import com.snoopgame.devices.fragments.TakeDeviceFragment;
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
    private static final String IP = "192.168.0.106";
    private static final String URL_ORDER = "http://" + IP + ":8080/order/";
    private static final String URL_PHONE = "http://" + IP + ":8080/phone/";
    private static final String URL_EMPLOYEE = "http://" + IP + ":8080/employee/get";
    private OkHttpClient client;
    private Request request;
    private String responseString;
    private Orders orders;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public HttpClient() {
        client = new OkHttpClient();
    }

    public void doGetRequestOrders(DashboardFragment dashboardFragment, PutDeviceFragment putDeviceFragment, String action) {
        request = new Request.Builder()
                .url(URL_ORDER + action)
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
                    if (dashboardFragment != null) {
                        if (dashboardFragment.getActivity() != null) {
                            dashboardFragment.getActivity().runOnUiThread(() -> {
                                for (int i = 0; i < orders.getAndroidOrders().size(); i++) {
                                    Order o = orders.getAndroidOrders().get(i);
                                    dashboardFragment.androidList.add(buildStringOrder(o.getPhone().getName(), o.getPhone().getFirmware_name(),
                                            o.getPhone().getFirmware_version(), o.getEmployee().getSurname(), o.getEmployee().getName(),
                                            o.getEmployee().getMiddleName(), o.getDate_start()));
                                }
                                for (int i = 0; i < orders.getiOSOrders().size(); i++) {
                                    Order o = orders.getiOSOrders().get(i);
                                    dashboardFragment.iOSList.add(buildStringOrder(o.getPhone().getName(), o.getPhone().getFirmware_name(),
                                            o.getPhone().getFirmware_version(), o.getEmployee().getSurname(), o.getEmployee().getName(),
                                            o.getEmployee().getMiddleName(), o.getDate_start()));
                                }
                                for (int i = 0; i < orders.getAmazonOrders().size(); i++) {
                                    Order o = orders.getAmazonOrders().get(i);
                                    dashboardFragment.amazonList.add(buildStringOrder(o.getPhone().getName(), o.getPhone().getFirmware_name(),
                                            o.getPhone().getFirmware_version(), o.getEmployee().getSurname(), o.getEmployee().getName(),
                                            o.getEmployee().getMiddleName(), o.getDate_start()));
                                }
                                dashboardFragment.setExpandableListView();
                            });
                        }
                    } else {
                        if (putDeviceFragment.getActivity() != null) {
                            putDeviceFragment.getActivity().runOnUiThread(() -> {
                                putDeviceFragment.put_components = new String[orders.getAndroidOrders().size()];
                                for (int i = 0; i < orders.getAndroidOrders().size(); i++) {
                                    Order o = orders.getAndroidOrders().get(i);
                                    putDeviceFragment.put_components[i] = buildStringOrder(o.getPhone().getName(),
                                            o.getPhone().getFirmware_name(), o.getPhone().getFirmware_version(), o.getEmployee().getSurname(),
                                            o.getEmployee().getName(), o.getEmployee().getMiddleName(), o.getDate_start());
                                }
                                putDeviceFragment.setListView(putDeviceFragment.put_components, null);
                            });
                        }

                    }
                }
            }
        });
    }

    public void doGetRequestPhone(DashboardFragment dashboardFragment, TakeDeviceFragment takeDeviceFragment, String action) {
        request = new Request.Builder()
                .url(URL_PHONE + action)
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
                    if (dashboardFragment != null) {
                        if (dashboardFragment.getActivity() != null) {
                            dashboardFragment.getActivity().runOnUiThread(() -> {
                                for (int i = 0; i < phones.getAndroidPhones().size(); i++) {
                                    Phone p = phones.getAndroidPhones().get(i);
                                    dashboardFragment.androidList.add(buildStringPhone(p.getName(), p.getFirmware_name(), p.getFirmware_version(),
                                            p.getFree_phone_amount()));
                                }
                                for (int i = 0; i < phones.getiOSPhones().size(); i++) {
                                    Phone p = phones.getiOSPhones().get(i);
                                    dashboardFragment.iOSList.add(buildStringPhone(p.getName(), p.getFirmware_name(), p.getFirmware_version(),
                                            p.getFree_phone_amount()));
                                }
                                for (int i = 0; i < phones.getAmazonPhones().size(); i++) {
                                    Phone p = phones.getAmazonPhones().get(i);
                                    dashboardFragment.amazonList.add(buildStringPhone(p.getName(), p.getFirmware_name(), p.getFirmware_version(),
                                            p.getFree_phone_amount()));
                                }
                            });
                            doGetRequestOrders(dashboardFragment, null, "getByFirmware");
                        }
                    } else {
                        if (takeDeviceFragment.getActivity() != null) {
                            takeDeviceFragment.getActivity().runOnUiThread(() -> {
                                takeDeviceFragment.phone_components = new String[phones.getAndroidPhones().size()];
                                for (int i = 0; i < phones.getAndroidPhones().size(); i++) {
                                    Phone p = phones.getAndroidPhones().get(i);
                                    takeDeviceFragment.phone_components[i] =
                                            buildStringPhone(p.getName(), p.getFirmware_name(), p.getFirmware_version(), p.getFree_phone_amount());
                                }
                                takeDeviceFragment.setPhoneListView(takeDeviceFragment.phone_components, null);
                                takeDeviceFragment.getTakeActivity().setMaterialSearchView("Phone");
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
                            takeDeviceFragment.employee_components[i] =
                                    "Surname: " + e.getSurname() + "\n" +
                                            "Name: " + e.getName() + "\n" +
                                            "Middle name: " + e.getMiddleName();
                        }
                        takeDeviceFragment.setEmployeeListView(takeDeviceFragment.employee_components, null);
                    });
                }
            }
        });
    }

    public void doPostRequestOrder(String action, Order order) {

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

    private String buildStringPhone(String name, String firmware_name, String version, int free_phone_amount) {
        return "Name: " + name + "\n" +
                "Firmware: " + firmware_name + "\n" +
                "Version: " + version + "\n" +
                "Amount: " + free_phone_amount;
    }

    private String buildStringOrder(String phone_name, String firmware_name, String version, String surname, String name, String middleName, String date) {
        return "Phone: " + phone_name + "\n" +
                "Firmware: " + firmware_name + "\n" +
                "Version: " + version + "\n" +
                "Surname: " + surname + "\n" +
                "Name: " + name + "\n" +
                "Middle name: " + middleName + "\n" +
                "Date of issue: " + date;
    }
}