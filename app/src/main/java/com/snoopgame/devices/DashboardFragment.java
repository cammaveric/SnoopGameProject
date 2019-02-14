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

public class DashboardFragment extends Fragment {

    public ListView listView;
    public String [] dash_components;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CreateTestJSON createTestJSON =new CreateTestJSON();
       Connect();
    }
    public void setListView(){
    listView=(ListView)getView().findViewById(R.id.dash_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(),R.layout.dash_components,dash_components);
        listView.setAdapter(adapter);
    }
    private void Connect(){
        HttpClient client = new HttpClient("https://reqres.in/api/users?page=2");
       client.doGetRequestDashboard(this);

    }
}
