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
import com.snoopgame.devices.fragments.PutDeviceFragment;

import java.util.ArrayList;

public class PutActivity extends AppCompatActivity {
    private MaterialSearchView materialSearchView;
    private PutDeviceFragment putDeviceFragment;
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            menuItem -> {
                Intent intent = null;
                switch (menuItem.getItemId()) {
                    case R.id.nav_takeDevice:
                        intent = new Intent(this, TakeActivity.class);
                        break;
                    case R.id.nav_putDevice:
                        putDeviceFragment.connect();
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
        setContentView(R.layout.activity_put);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation_put);
        putDeviceFragment = new PutDeviceFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_put, putDeviceFragment).commit();
        bottomNav.setSelectedItemId(R.id.nav_putDevice);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        setMaterialSearchView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        materialSearchView.setMenuItem(menuItem);
        return true;
    }

    private void setMaterialSearchView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Order search");
        toolbar.setTitleTextColor(Color.WHITE);
        materialSearchView = findViewById(R.id.search_view);

        materialSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {
                putDeviceFragment.setListView(putDeviceFragment.put_components, null);
            }
        });
        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null && !newText.isEmpty()) {
                    ArrayList<String> listFound = new ArrayList<>();
                    for (String item : putDeviceFragment.put_components) {
                        if (item.contains(newText)) {
                            listFound.add(item);
                        }
                    }
                    putDeviceFragment.setListView(null, listFound);
                } else {
                    putDeviceFragment.setListView(putDeviceFragment.put_components, null);
                }
                return true;
            }
        });
    }
}
