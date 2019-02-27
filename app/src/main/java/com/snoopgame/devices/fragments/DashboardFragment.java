package com.snoopgame.devices.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.snoopgame.devices.adapters.ExpandableListAdapter;
import com.snoopgame.devices.R;
import com.snoopgame.devices.connection.HttpClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DashboardFragment extends Fragment {
    private List<String> listDataHeader =new ArrayList<>();
    private HashMap<String,List<String>> listHashMap=new HashMap<>();
    public List<String> androidList=new ArrayList<>();
    public List<String> iOSList=new ArrayList<>();
    public List<String> amazonList=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Connect();
    }

   private void Connect() {
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HttpClient client = new HttpClient();
        client.doGetRequestPhone(this, null,"getByFirmware");

    }
    public void setExpandableListView(){
        ExpandableListView expandableListView=getActivity().findViewById(R.id.dash_list);
        listDataHeader.add("Android");
        listDataHeader.add("iOS");
        listDataHeader.add("Amazon");
        listHashMap.put(listDataHeader.get(0),androidList);
        listHashMap.put(listDataHeader.get(1),iOSList);
        listHashMap.put(listDataHeader.get(2),amazonList);
        ExpandableListAdapter expandableListAdapter=new ExpandableListAdapter(this.getContext(),listDataHeader,listHashMap);
        expandableListView.setAdapter(expandableListAdapter);
    }

}
