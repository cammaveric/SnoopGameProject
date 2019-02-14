package com.snoopgame.devices.connection;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.snoopgame.devices.DashboardFragment;
import com.snoopgame.devices.PutDeviceFragment;
import com.snoopgame.devices.R;
import com.snoopgame.devices.TakeDeviceFragment;
import com.snoopgame.devices.objectsForJSON.Employee;
import com.snoopgame.devices.objectsForJSON.Order;
import com.snoopgame.devices.objectsForJSON.Orders;
import com.snoopgame.devices.objectsForJSON.Phone;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpClient {

    private OkHttpClient client;
    private Request request;
    private String url;
    private String responseString;
    Orders orders;

    public HttpClient(String url) {
        client = new OkHttpClient();
        this.url = url;
    }

    public void doGetRequestDashboard(DashboardFragment dashboardFragment) {
        request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("connClose", Arrays.toString(e.getStackTrace()));
                }

            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                   // try {
                        //responseString = response.body().string();
                        responseString="{\n" +
                                "      \"orders\": [\n" +
                                "        {\n" +
                                "          \"date_end\": \"янв. 1, 1970\",\n" +
                                "          \"date_start\": \"янв. 1, 1970\",\n" +
                                "          \"employee\": {\n" +
                                "            \"id\": 1,\n" +
                                "            \"middleName\": \"asdsd\",\n" +
                                "            \"name\": \"jora\",\n" +
                                "            \"surname\": \"asd\"\n" +
                                "          },\n" +
                                "          \"id\": 1,\n" +
                                "          \"phone\": {\n" +
                                "            \"id\": 1,\n" +
                                "            \"name\": \"Samsung\"\n" +
                                "          },\n" +
                                "          \"status\": \"executed\"\n" +
                                "        }\n" +
                                "      ]\n" +
                                "    }";
                        Gson gson = new Gson();
                        orders = gson.fromJson(responseString,Orders.class);
                  // } catch (IOException e) {
                  //      Log.e("connClose", Arrays.toString(e.getStackTrace()));
                   // }
                   // Log.i("response", responseString);
                    dashboardFragment.getActivity().runOnUiThread(() -> {
                            dashboardFragment.dash_components=new String[orders.getOrders().size()];
                            for (int i=0;i<orders.getOrders().size();i++) {
                                dashboardFragment.dash_components[i] = "Дата получения: "+orders.getOrders().get(i).getDate_start()+"\n"+
                                        "ФИО: "+ orders.getOrders().get(i).getEmployee().getSurname()+
                                        " "+ orders.getOrders().get(i).getEmployee().getName()+
                                        " "+orders.getOrders().get(i).getEmployee().getMiddleName()+"\n"+
                                        "Телефон: " +orders.getOrders().get(i).getPhone().getName();
                            }
                            dashboardFragment.setListView();
                    });
                }
            }
        });
    }

   /* public void doGetRequestPut(PutDeviceFragment putDeviceFragment) {
        request = new Request.Builder()
                .url(url)
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
                    Log.i("response", responseString);
                    putDeviceFragment.getActivity().runOnUiThread(() -> txtView.setText(response));
                }
            }
        });
    }
    public String doGetRequestTake(TakeDeviceFragment takeDeviceFragment) {
        request = new Request.Builder()
                .url(url)
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
                    Log.i("response", responseString);
                    takeDeviceFragment.getActivity().runOnUiThread(() -> takeDeviceFragment.txtView.setText(response));
                }
            }
        });
    }*/


    public void doPostRequestTake(TakeDeviceFragment putDeviceFragment) {
        Employee employee=new Employee(1,"'jora","asd","asdsd");
        Phone phone=new Phone(1,"Samsung");
        Date date_s=new Date(2019,02,14);/*Этот конструктор устарел и не робит, но и новый тоже не работает*/
        Order order=new  Order(1, employee,phone,date_s,null,"initiated");
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        String json=gson.toJson(order);
        Log.i("json",json);
        String jsonToServer = "{\n" +
                "    \"name\": \"Melardev\",\n" +
                "    \"job\": \"Student\"\n" +
                "}";
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonToServer);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("connClose", Arrays.toString(e.getStackTrace()));
            }

            @Override
            public void onResponse(Call call, Response response)  {
                if (response.isSuccessful()) {
                    try {
                        responseString = response.body().string();
                    } catch (IOException e) {
                        Log.e("connClose", Arrays.toString(e.getStackTrace()));
                    }
                    Log.i("response", responseString);
                    putDeviceFragment.getActivity().runOnUiThread(() -> putDeviceFragment.txtView.setText(responseString));
                }
            }
        });
    }
}
