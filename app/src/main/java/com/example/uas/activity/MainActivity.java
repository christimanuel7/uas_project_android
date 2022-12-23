package com.example.uas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.uas.R;
import com.example.uas.fragment.GlobalFragment;
import com.example.uas.fragment.HomeFragment;
import com.example.uas.retrofitAPI.ApiEndpoint;
import com.example.uas.retrofitAPI.ApiService;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Retrofit retrofit = ApiService.getClient();
        ApiEndpoint apiEndpoint = retrofit.create(ApiEndpoint.class);

        drawerLayout = findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();
        toolbar.setTitle("CONTINENT");

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.menu_menu);
        }
    }

    //    SELECT ITEM ON NAV DRAWER
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_menu:
                navigationView.setCheckedItem(R.id.menu_menu);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
                toolbar.setTitle("CONTINENT");
                break;
            case R.id.menu_global:
                navigationView.setCheckedItem(R.id.menu_global);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new GlobalFragment()).commit();
                toolbar.setTitle("GLOBAL COUNTRY");
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}