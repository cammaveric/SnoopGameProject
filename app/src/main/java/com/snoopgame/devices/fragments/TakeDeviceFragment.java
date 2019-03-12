package com.snoopgame.devices.fragments;

import android.app.AlertDialog;
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
import com.snoopgame.devices.activities.TakeActivity;
import com.snoopgame.devices.connection.HttpClient;
import com.snoopgame.devices.objectsForJSON.Employee;
import com.snoopgame.devices.objectsForJSON.Order;
import com.snoopgame.devices.objectsForJSON.Phone;
import com.snoopgame.devices.objectsForJSON.Status;

import java.util.ArrayList;
import java.util.Collections;

public class TakeDeviceFragment extends Fragment {

    public ListView listView;
    public String[] employee_components;
    public String[] phone_components;
    private String employee_name;
    private String employee_surname;
    private String employee_middleName;
    private HttpClient client;
    private TakeActivity takeActivity;

    public TakeActivity getTakeActivity() {
        return takeActivity;
    }

    public void setTakeActivity(TakeActivity takeActivity) {
        this.takeActivity = takeActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_take_device, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        connect();
    }

    public void setEmployeeListView(String[] array_employee_components, ArrayList<String> list_employee_found) {
        listView = getView().findViewById(R.id.take_list);
        if (array_employee_components != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), R.layout.component, array_employee_components);
            listView.setAdapter(adapter);
        } else {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), R.layout.component, list_employee_found);
            listView.setAdapter(adapter);
        }
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String val = (String) listView.getItemAtPosition(position);
            StringBuilder surname = new StringBuilder();
            StringBuilder name = new StringBuilder();
            StringBuilder middleName = new StringBuilder();
            for (int i = 9; i < val.length(); i++) {
                if (val.charAt(i) == '\n') break;
                surname.append(val.charAt(i));
            }
            for (int i = 9 + surname.length() + 7; i < val.length(); i++) {
                if (val.charAt(i) == '\n') break;
                name.append(val.charAt(i));
            }
            for (int i = 9 + surname.length() + 7 + name.length() + 14; i < val.length(); i++) {
                if (val.charAt(i) == '\n') break;
                middleName.append(val.charAt(i));
            }
            employee_name = name.toString();
            employee_surname = surname.toString();
            employee_middleName = middleName.toString();

            client.doGetRequestPhone(null, this, "getAll");
        });
    }

    public void setPhoneListView(String[] array_phone_components, ArrayList<String> list_phone_found) {
        listView = getView().findViewById(R.id.take_list);
        if (array_phone_components != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), R.layout.component, array_phone_components);
            listView.setAdapter(adapter);
        } else {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), R.layout.component, list_phone_found);
            listView.setAdapter(adapter);
        }
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String val = (String) listView.getItemAtPosition(position);
            StringBuilder name = new StringBuilder();
            StringBuilder firmware = new StringBuilder();
            StringBuilder version = new StringBuilder();
            for (int i = 6; i < val.length(); i++) {
                if (val.charAt(i) == '\n') break;
                name.append(val.charAt(i));
            }
            for (int i = 6 + name.length() + 11; i < val.length(); i++) {
                if (val.charAt(i) == '\n') break;
                firmware.append(val.charAt(i));
            }
            for (int i = 6 + name.length() + 11 + firmware.length() + 10; i < val.length(); i++) {
                if (val.charAt(i) == '\n') break;
                version.append(val.charAt(i));
            }
            client.doPostRequestOrder("add",
                    new Order(0, null, null,
                            new Employee(1, employee_name, employee_surname, employee_middleName),
                            new Phone(0, name.toString(), 1, 1, firmware.toString(), version.toString()),
                            Collections.singleton(Status.INITIATED)));
            getActivity().startActivity(new Intent(this.getActivity(), MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        });
    }

    public void connect() {
        client = new HttpClient();
        client.doGetRequestEmployee(this);
    }
}
