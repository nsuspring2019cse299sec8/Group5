package com.rentalservice.renalservice;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import static android.view.View.INVISIBLE;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";

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
        addTenant = findViewById(R.id.add_tenant_btn);
        String ch =getIntent().getStringExtra("owner_key");

        if(ch!=null){
            if(ch.equals("owner")){
                bool = true;
                Log.d(TAG, "init: owner found ");
                addTenant.show();
                addTenant.setEnabled(true);
                addTenant.setClickable(true);
                addTenant.setAlpha(1.0f);

            }else{
                addTenant.setEnabled(false);
                addTenant.setClickable(false);
                addTenant.setAlpha(0.3f);
            }
        }else{
            addTenant.hide();
            //addTenant.setVisibility(INVISIBLE);
            addTenant.setEnabled(false);
            addTenant.setClickable(false);
            addTenant.setAlpha(0.3f);
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
        else if (id == R.id.nav_add_tenant) {

        }
        else if (id == R.id.nav_update_profile) {
            Intent goUpdate = new Intent(MainActivity.this, UpdateProfile.class);
            if (bool){
                goUpdate.putExtra("key","owner");
            }else{
                goUpdate.putExtra("key","tenant");
            }
            startActivity(goUpdate);


        }
            //goto nav add tenant
        return false;
    }
}
