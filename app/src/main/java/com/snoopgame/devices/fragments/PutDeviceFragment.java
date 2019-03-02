package com.snoopgame.devices.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.snoopgame.devices.R;
import com.snoopgame.devices.activities.MainActivity;
import com.snoopgame.devices.connection.HttpClient;
import com.snoopgame.devices.objectsForJSON.Employee;
import com.snoopgame.devices.objectsForJSON.Order;
import com.snoopgame.devices.objectsForJSON.Phone;

import java.util.ArrayList;

public class PutDeviceFragment extends Fragment {
    public String[] put_components;
    private ListView listView;
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
        connect();
    }

    public void setListView(String[] array_put_components, ArrayList<String> list_put_found) {
        listView = getView().findViewById(R.id.put_list);
        if (array_put_components != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), R.layout.component, array_put_components);
            listView.setAdapter(adapter);
        } else {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), R.layout.component, list_put_found);
            listView.setAdapter(adapter);
        }
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String val = (String) listView.getItemAtPosition(position);
            StringBuilder phone = new StringBuilder();
            StringBuilder firmware = new StringBuilder();
            StringBuilder version = new StringBuilder();
            StringBuilder surname = new StringBuilder();
            StringBuilder name = new StringBuilder();
            StringBuilder middleName = new StringBuilder();
            StringBuilder date = new StringBuilder();
            for (int i = 7; i < val.length(); i++) {
                if (val.charAt(i) == '\n') break;
                phone.append(val.charAt(i));
            }
            for (int i = 7 + phone.length() + 11; i < val.length(); i++) {
                if (val.charAt(i) == '\n') break;
                firmware.append(val.charAt(i));
            }
            for (int i = 7 + phone.length() + 11 + firmware.length() + 10; i < val.length(); i++) {
                if (val.charAt(i) == '\n') break;
                version.append(val.charAt(i));
            }
            for (int i = 7 + phone.length() + 11 + firmware.length() + 13 + version.length() + 7; i < val.length(); i++) {
                if (val.charAt(i) == '\n') break;
                surname.append(val.charAt(i));
            }
            for (int i = 7 + phone.length() + 11 + firmware.length() + 13 + version.length() + 7 + surname.length() + 7; i < val.length(); i++) {
                if (val.charAt(i) == '\n') break;
                name.append(val.charAt(i));
            }
            for (int i = 7 + phone.length() + 11 + firmware.length() + 13 + version.length() + 7 + surname.length() + 7 + name.length() + 14; i < val.length(); i++) {
                if (val.charAt(i) == '\n') break;
                middleName.append(val.charAt(i));
            }
            for (int i = 7 + phone.length() + 11 + firmware.length() + 13 + version.length() + 7 + surname.length() + 7 + name.length() + 14 + middleName.length() + 16; i < val.length(); i++) {
                if (val.charAt(i) == '\n') break;
                date.append(val.charAt(i));
            }
            client.doPostRequestOrder("update",
                    new Order(0, date.toString(), null,
                            new Employee(0, name.toString(), surname.toString(), middleName.toString()),
                            new Phone(0, phone.toString(), 0, 0, firmware.toString(),
                                    version.toString()), null));
            getActivity().startActivity(new Intent(this.getActivity(), MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));


        });
    }

    public void connect() {
        client = new HttpClient();
        client.doGetRequestOrders(null, this, "getAll");
    }

}
