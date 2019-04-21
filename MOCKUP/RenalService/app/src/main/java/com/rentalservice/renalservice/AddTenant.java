package com.rentalservice.renalservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class AddTenant extends AppCompatActivity {
   EditText name_et, phone_et, nid_et,prev_add_et;
   Button updatebtn;
   Owner owner;
   Tenant tenant;
   String chectStr;
   boolean check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tenant_info);

        init();

    }

    private void init() {

        name_et = findViewById(R.id.name_et);
        nid_et = findViewById(R.id.nid_et);
        prev_add_et= findViewById(R.id.prev_et);
        phone_et= findViewById(R.id.phone_et);
        updatebtn =findViewById(R.id.update_btn);
        try{
            chectStr = getIntent().getStringExtra('key');
            if (chectStr.equals("owner")){
                //update owners
            }elseif(chectStr.equals("tenant")){
               //update tenant profile
            
            }

        }catch (NullPointerException e){
            Log.d(TAG, "init: "e.getMessage());
        }
    }
}
