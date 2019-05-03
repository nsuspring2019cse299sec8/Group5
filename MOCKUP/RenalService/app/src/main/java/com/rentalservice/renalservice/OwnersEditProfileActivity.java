package com.rentalservice.renalservice;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OwnersEditProfileActivity extends AppCompatActivity {
    private static final String TAG = "OwnersEditProfileActivi";

    private ImageButton ownersProfileImageView;
    private TextView ownersNameEditText;
    private TextView ownersAreaEditText;
    private TextView ownersEmailEditText;
    private TextView ownersPhoneEditText;
    private Button submit;

    FirebaseUser mUser;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owners_edit_profile);

        init();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ownersNameEditText.getText().toString();
                String email = ownersNameEditText.getText().toString();
                String phone = ownersNameEditText.getText().toString();
                String area = ownersNameEditText.getText().toString();
                Owner owner = new Owner(name, email , phone, area);

                owner.setTotal_house("0");

                myRef =database.getReference();

                myRef.child("owners").child(user_id).setValue(owner).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(OwnersEditProfileActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                            Intent goIntent = new Intent(OwnersEditProfileActivity.this, OwnersActivity.class);
                            startActivity(goIntent);
                            finish();

                        }else{
                            Toast.makeText(OwnersEditProfileActivity.this, "Profile Update Fail!", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
    }

    private void init() {

        ownersProfileImageView = findViewById(R.id.owners_profile_imageview);
        ownersNameEditText = findViewById(R.id.owners_name_EditText);
        ownersEmailEditText = findViewById(R.id.owners_email_EditText);
        ownersPhoneEditText = findViewById(R.id.owners_phone_EditText);
        ownersAreaEditText = findViewById(R.id.owners_home_EditText);

        submit = findViewById(R.id.submit_btn_id);

        mAuth =FirebaseAuth.getInstance();
        mUser =mAuth.getCurrentUser();
        try{
            user_id =mUser.getUid();
            Log.d(TAG, "init: check str: ");
        }catch (NullPointerException e){
            Log.d(TAG, "init: "+e.getMessage());
        }
        database =FirebaseDatabase.getInstance();
    }
}
