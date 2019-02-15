package com.snoopgame.devices;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.snoopgame.devices.connection.HttpClient;
import com.snoopgame.devices.objectsForJSON.Order;

public class PutDeviceFragment extends Fragment {
    private ListView listView;
    public String [] put_components;
    public int [] id_orders;
    private HttpClient client;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_put_device, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Connect();
    }
    public void setListView(){
        listView=getView().findViewById(R.id.put_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(),R.layout.components,put_components);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            client.doPostRequestOrder("update",
                    new Order(id_orders[position], null, null, null, null, "executed"));
            });
    }

    public void Connect(){
        client = new HttpClient();
        client.doGetRequestOrders(null,this);
    }

}
