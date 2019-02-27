package com.snoopgame.devices.activities;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.snoopgame.devices.R;
import com.snoopgame.devices.fragments.DashboardFragment;
import com.snoopgame.devices.fragments.PutDeviceFragment;
import com.snoopgame.devices.fragments.TakeDeviceFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new DashboardFragment()).commit();
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_dashboard);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            menuItem -> {
                Fragment selectedFragment = null;

                switch (menuItem.getItemId()){
                    case R.id.nav_takeDevice:
                        selectedFragment = new TakeDeviceFragment();
                        break;
                    case R.id.nav_putDevice:
                        selectedFragment = new PutDeviceFragment();
                        break;
                    case R.id.nav_dashboard:
                        selectedFragment = new DashboardFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedFragment).commit();

                return true;
            };
}
