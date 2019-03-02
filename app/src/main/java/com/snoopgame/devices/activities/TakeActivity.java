package com.snoopgame.devices.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.snoopgame.devices.R;
import com.snoopgame.devices.fragments.TakeDeviceFragment;

import java.util.ArrayList;

public class TakeActivity extends AppCompatActivity {
    private MaterialSearchView materialSearchView;
    private TakeDeviceFragment takeDeviceFragment;
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            menuItem -> {
                Intent intent = null;
                switch (menuItem.getItemId()) {
                    case R.id.nav_takeDevice:
                        takeDeviceFragment.connect();
                        setMaterialSearchView("Employee");
                        break;
                    case R.id.nav_putDevice:
                        intent = new Intent(this, PutActivity.class);
                        break;
                    case R.id.nav_dashboard:
                        intent = new Intent(this, MainActivity.class);
                        break;
                }
                if (intent != null) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }


                return true;
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation_take);
        takeDeviceFragment = new TakeDeviceFragment();
        takeDeviceFragment.setTakeActivity(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_take, takeDeviceFragment).commit();
        bottomNav.setSelectedItemId(R.id.nav_takeDevice);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        setMaterialSearchView("Employee");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        materialSearchView.setMenuItem(menuItem);
        return true;
    }

    public void setMaterialSearchView(String name) {
        Toolbar toolbar = findViewById(R.id.toolbar_take);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(name + " search");
        toolbar.setTitleTextColor(Color.WHITE);
        materialSearchView = findViewById(R.id.search_view_take);

        materialSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {
                if (name.equals("Employee")) {
                    takeDeviceFragment.setEmployeeListView(takeDeviceFragment.employee_components, null);
                } else {
                    takeDeviceFragment.setPhoneListView(takeDeviceFragment.phone_components, null);
                }
            }
        });
        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (name.equals("Employee")) {
                    if (newText != null && !newText.isEmpty()) {
                        ArrayList<String> listFound = new ArrayList<>();
                        for (String item : takeDeviceFragment.employee_components) {
                            if (item.contains(newText)) {
                                listFound.add(item);
                            }
                        }
                        takeDeviceFragment.setEmployeeListView(null, listFound);
                    } else {
                        takeDeviceFragment.setEmployeeListView(takeDeviceFragment.employee_components, null);
                    }
                    return true;
                } else {
                    if (newText != null && !newText.isEmpty()) {
                        ArrayList<String> listFound = new ArrayList<>();
                        for (String item : takeDeviceFragment.phone_components) {
                            if (item.contains(newText)) {
                                listFound.add(item);
                            }
                        }
                        takeDeviceFragment.setPhoneListView(null, listFound);
                    } else {
                        takeDeviceFragment.setPhoneListView(takeDeviceFragment.phone_components, null);
                    }
                    return true;
                }
            }
        });

    }
}