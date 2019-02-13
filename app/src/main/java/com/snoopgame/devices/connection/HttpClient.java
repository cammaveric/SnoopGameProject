package com.snoopgame.devices.connection;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpClient {

    private OkHttpClient client;
    private Request request;
    private String url = "https://reqres.in/api/users?page=2";

    public HttpClient(){
        client = new OkHttpClient();
    }

    public void sendGetRequest(){

        request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback( ) {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    String myResponse = response.body().string();
                    System.out.println(myResponse);
                }
            }
        });
    }
}
