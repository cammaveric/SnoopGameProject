package com.snoopgame.devices;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.snoopgame.devices.connection.HttpClient;

public class TakeDeviceFragment extends Fragment {
    public TextView txtView;
    public static final String  URL="http://192.168.0.94:8080/take";
    //урл данила public static final String  URL="http://192.168.0.94:8080/orders";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_take_device, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    txtView = (TextView)view.getRootView().findViewById(R.id.putView);
        Connect();
    }

    public void Connect(){
        HttpClient client = new HttpClient(URL);
        client.doPostRequestTake(this);
    }
}
