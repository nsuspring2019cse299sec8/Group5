package com.rentalservice.renalservice;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TenantEditProfileActivity extends AppCompatActivity {
    private static final String TAG = "TenantEditProfileActivity";

    private ImageButton ownersProfileImageView;
    private TextView tenantNameEditText;
    private TextView tenantAreaEditText;
    private TextView tenantEmailEditText;
    private TextView tenantPhoneEditText;
    private Button submit;

    FirebaseUser mUser;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_edit_profile);

        init();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = tenantNameEditText.getText().toString();
                String email = mUser.getEmail();
                String phone = tenantPhoneEditText.getText().toString();
                String area = tenantAreaEditText.getText().toString();

                Tenant tenant = new Tenant(name,phone,area,false);


                myRef =database.getReference();

                myRef.child("tenants").child(user_id).setValue(tenant).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(TenantEditProfileActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                            Intent goIntent = new Intent(TenantEditProfileActivity.this, MainActivity.class);
                            startActivity(goIntent);
                            finish();

                        }else{
                            Toast.makeText(TenantEditProfileActivity.this, "Profile Update Fail!", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
    }

    private void init() {

        ownersProfileImageView = findViewById(R.id.owners_profile_imageview);
        tenantNameEditText = findViewById(R.id.tenant_name_EditText);
        tenantPhoneEditText = findViewById(R.id.tenant_phone_EditText);
        tenantAreaEditText = findViewById(R.id.tenant_home_EditText);

        submit = findViewById(R.id.submit_btn_id);

        mAuth =FirebaseAuth.getInstance();
        mUser =mAuth.getCurrentUser();
        try{
            user_id =mUser.getUid();

        }catch (NullPointerException e){

        }
        database =FirebaseDatabase.getInstance();
    }
}
