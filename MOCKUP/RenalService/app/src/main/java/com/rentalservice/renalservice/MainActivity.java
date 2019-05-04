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
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.view.View.INVISIBLE;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";

    TextView tenantName,tenantArea;

    DrawerLayout mDrawer;
    NavigationView mNaviagtion;
    boolean bool =false;
    FloatingActionButton addTenant;

    FirebaseDatabase database;
    DatabaseReference myRef;

    FirebaseUser mUser;
    FirebaseAuth mAuth;

    String user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       init();

        // Read from the database
        myRef.child(user_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Tenant tenant = dataSnapshot.getValue(Tenant.class);
                //Log.d(TAG, "Value is: " + owner.toString());
                tenantName.setText(tenant.getName());
                tenantArea.setText(tenant.getPrev_address());

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    private void init() {
        mDrawer = findViewById(R.id.drawer_layout);
        mNaviagtion = findViewById(R.id.nav_view);
        mNaviagtion.setNavigationItemSelectedListener(MainActivity.this);
        addTenant = findViewById(R.id.add_tenant_btn);

        tenantName = findViewById(R.id.username_tv);
        tenantArea = findViewById(R.id.tenant_area_tv);

        database =FirebaseDatabase.getInstance();
        myRef = database.getReference("tenants");
        mAuth =FirebaseAuth.getInstance();
        mUser =mAuth.getCurrentUser();
        try{
            user_id =mUser.getUid();
            Log.d(TAG, "init: check str: ");
        }catch (NullPointerException e){
            Log.d(TAG, "init: "+e.getMessage());
        }


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
