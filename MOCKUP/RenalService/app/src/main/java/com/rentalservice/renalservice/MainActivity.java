package com.rentalservice.renalservice;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout mDrawer;
    NavigationView mNaviagtion;
    boolean bool =false;
    FloatingActionButton addTenant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       init();
    }

    private void init() {
        mDrawer = findViewById(R.id.drawer_layout);
        mNaviagtion = findViewById(R.id.nav_view);
        mNaviagtion.setNavigationItemSelectedListener(MainActivity.this);

        if(getIntent().getStringArrayExtra("owner_key").equals("owner")){
            bool = true;
            addTenant = findViewById(R.id.add_tenant_btn);
           addTenant.show();

        }else{
            addTenant.hide();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();

        if (id == R.id.nav_dashboard) {
            // Handle the camera action
            Intent goMain = new Intent(MainActivity.this,MainActivity.class);
            startActivity(goMain);
            finish();
        } else if (id == R.id.nav_rent) {
            //goto my rent activity

        } else if (id == R.id.nav_myrental){
            //goto nav my rental
        }
        return false;
    }
}
