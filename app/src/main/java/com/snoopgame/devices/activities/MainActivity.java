package com.snoopgame.devices.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

import com.snoopgame.devices.R;
import com.snoopgame.devices.fragments.DashboardFragment;

public class MainActivity extends AppCompatActivity {
    private DashboardFragment dashboardFragment;
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            menuItem -> {
                Intent intent = null;

                switch (menuItem.getItemId()) {
                    case R.id.nav_takeDevice:
                        intent = new Intent(this, TakeActivity.class);
                        break;
                    case R.id.nav_putDevice:
                        intent = new Intent(this, PutActivity.class);
                        break;
                    case R.id.nav_dashboard:
                        dashboardFragment.clearListView();
                        dashboardFragment.connect();
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
        setContentView(R.layout.activity_first);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        dashboardFragment = new DashboardFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, dashboardFragment).commit();
        bottomNav.setSelectedItemId(R.id.nav_dashboard);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }
}
