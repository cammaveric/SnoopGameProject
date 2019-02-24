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
import com.snoopgame.devices.objectsForJSON.Status;

import java.util.Collections;

public class PutDeviceFragment extends Fragment {
    private ListView listView;
    public String [] put_components;
    public long id_order;
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
            String val=(String) listView.getItemAtPosition(position);
            StringBuilder stringBuilder=new StringBuilder();
            for (int i=4;i<val.length();i++){
                if (val.charAt(i)=='\n') break;
                stringBuilder.append(val.charAt(i));
            }
            id_order=Integer.parseInt(stringBuilder.toString());
                client.doPostRequestOrder("update",
                        new Order(id_order, null, null, null, null, Collections.singleton(Status.EXECUTED)));

        });
    }

    public void Connect(){
        client = new HttpClient();
        client.doGetRequestOrders(null,this);
    }

}
