package com.rentalservice.renalservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateProfile extends AppCompatActivity {
    private static final String TAG = "UpdateProfile";
   EditText name_et, phone_et, nid_et,prev_add_et;
   Button updatebtn;
   Owner owner;
   Tenant tenant;
   String chectStr;
   boolean check;
   FirebaseUser mUser;
   FirebaseAuth mAuth;
   FirebaseDatabase database;
   DatabaseReference myRef;
   private String user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tenant_info);

        init();

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update_profile();
            }
        });

    }

    private void update_profile() {

        String name =name_et.getText().toString();
        String phone = phone_et.getText().toString();
        String nid = nid_et.getText().toString();
        String prev_add = prev_add_et.getText().toString();

        if (check){
            owner =new Owner();
            owner.setName(name);
            owner.setPhone(phone);
            owner.setHouse_address(prev_add);
            owner.setNid(nid);
            myRef.child("owners").child(user_id).setValue(owner);
            Toast.makeText(this, "Updating Profile", Toast.LENGTH_SHORT).show();

        }else{

            tenant =new Tenant();
            tenant.setName(name);
            tenant.setPhone(phone);
            tenant.setPrev_address(prev_add);
            tenant.setNid(nid);
            myRef.child("tenant").child(user_id).setValue(tenant);



        }


    }

    private void init() {

        name_et = findViewById(R.id.name_et);
        nid_et = findViewById(R.id.nid_et);
        prev_add_et= findViewById(R.id.prev_et);
        phone_et= findViewById(R.id.phone_et);
        updatebtn =findViewById(R.id.update_btn);
        mAuth =FirebaseAuth.getInstance();
        mUser =mAuth.getCurrentUser();
        try{
            user_id =mUser.getUid();

            chectStr = getIntent().getStringExtra("key");
            Log.d(TAG, "init: check str: ");
            if ("owner".equals(chectStr)){
                //update owners
                check = true;
            }else if(chectStr.equals("tenant")){
               //update tenant profile

            }

        }catch (NullPointerException e){
            Log.d(TAG, "init: "+e.getMessage());
        }
        database =FirebaseDatabase.getInstance();
        myRef = database.getReference();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mUser =mAuth.getCurrentUser();
        Log.d(TAG, "onStart: user uiu "+mUser.getUid());
    }
}
