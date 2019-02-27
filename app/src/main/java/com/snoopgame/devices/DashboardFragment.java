package com.snoopgame.devices;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.snoopgame.devices.connection.HttpClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DashboardFragment extends Fragment {
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHashMap;
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
        initData();
        Connect();
    }

   private void Connect() {
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HttpClient client = new HttpClient();
        client.doGetRequestPhone(this, null);

    }
    public void setExpandableListView(){
        ExpandableListView expandableListView=getActivity().findViewById(R.id.dash_list);
        listHashMap.put(listDataHeader.get(0),androidList);
        listHashMap.put(listDataHeader.get(1),iOSList);
        listHashMap.put(listDataHeader.get(2),amazonList);
        ExpandableListAdapder expandableListAdapder=new ExpandableListAdapder(this.getContext(),listDataHeader,listHashMap);
        expandableListView.setAdapter(expandableListAdapder);
    }
    private void initData(){
        listDataHeader=new ArrayList<>();
        listHashMap=new HashMap<>();
        listDataHeader.add("Android");
        listDataHeader.add("iOS");
        listDataHeader.add("Amazon");

    }
}
