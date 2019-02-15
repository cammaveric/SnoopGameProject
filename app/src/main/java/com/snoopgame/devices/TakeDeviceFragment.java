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
import com.snoopgame.devices.objectsForJSON.Employee;
import com.snoopgame.devices.objectsForJSON.Order;
import com.snoopgame.devices.objectsForJSON.Phone;

public class TakeDeviceFragment extends Fragment {
    public ListView listView;
    public String [] employee_components;
    public String [] phone_components;
    public int[] employees_id;
    public int[] phones_id;
    private int employee_id;
    private int phone_id;
    private HttpClient client;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_take_device, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Connect();
    }
    public void setEmployeeListView(){
        listView=getView().findViewById(R.id.take_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(),R.layout.components,employee_components);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
        employee_id = employees_id[position];
        client.doGetRequestPhone(null,this);
        });
    }
    public void setPhoneListView(){
        listView=getView().findViewById(R.id.take_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(),R.layout.components,phone_components);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            phone_id = phones_id[position];
            client.doPostRequestOrder("add",
                    new Order(1,
                    new Employee(employee_id,null,null,null),
                    new Phone(phone_id,null,null,1),
                    null,null,"initiated"));/*На БД автоинкремент, вместо ID order будет NULL на сервере*/
        });
    }

    public void Connect(){
        client = new HttpClient();
        client.doGetRequestEmployee(this);
    }
}
