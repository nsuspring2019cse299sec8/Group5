package com.rentalservice.renalservice;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginForTenantActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    EditText emailet, passwordet;
    Button signinbtn, signupbtn;
    TextView welcomeTv;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private String check,user_id;
    private boolean bool=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            // User is signed in
            Intent goIntent = new Intent(LoginForTenantActivity.this, MainActivity.class);
            startActivity(goIntent);
            finish();
        } else {
            // No user is signed in
            init();
            signupbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "onClick: loginbutton");
                    if (!TextUtils.isEmpty(emailet.getText().toString()) &&
                            !TextUtils.isEmpty(passwordet.getText().toString())) {
                        String email = emailet.getText().toString();
                        String password = passwordet.getText().toString();
                        Log.i(TAG, "onClick: email: " + email + " pass: " + password);

                        register(email, password);

                    } else {
                        //fields are empty
                        Toast.makeText(LoginForTenantActivity.this, "Empty Field Not Allowed", Toast.LENGTH_SHORT).show();

                    }

                }
            });

            signinbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!TextUtils.isEmpty(emailet.getText().toString()) &&
                            !TextUtils.isEmpty(passwordet.getText().toString())) {
                        String email = emailet.getText().toString();
                        String password = passwordet.getText().toString();
                        Log.i(TAG, "onClick: email: " + email + " pass: " + password);

                        login(email, password);

                    } else {
                        //fields are empty
                    }
                }
            });

        }

    }

    private void register(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            mUser = mAuth.getCurrentUser();
                            Toast.makeText(LoginForTenantActivity.this, "Signed up successful", Toast.LENGTH_SHORT).show();
                            Intent goHome=new Intent(LoginForTenantActivity.this,TenantEditProfileActivity.class);
                            startActivity(goHome);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(LoginForTenantActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });

    }


    private void login(String email, String password) {
        Log.i(TAG, "login: login Auth called");
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            //yah we are in
                            mUser=mAuth.getCurrentUser();
                            Toast.makeText(LoginForTenantActivity.this, "Signed in successful", Toast.LENGTH_SHORT).show();
                            Intent goHome=new Intent(LoginForTenantActivity.this,MainActivity.class);
                            startActivity(goHome);
                            finish();

                        }else {
                            Toast.makeText(LoginForTenantActivity.this, "Signed failed!", Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }

    private void updateUI(Object o) {
        if (o==null){
            Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Successfully", Toast.LENGTH_SHORT).show();
            Intent goMain = new Intent(LoginForTenantActivity.this, MainActivity.class);
            startActivity(goMain);
            finish();

        }
    }

    private void init() {
        emailet = findViewById(R.id.email_et_id);
        passwordet = findViewById(R.id.pass_et_id);
        signinbtn = findViewById(R.id.sign_in_as_owner_btn_id);
        signupbtn = findViewById(R.id.sign_up_as_owner_btn_id);
        welcomeTv = findViewById(R.id.welcome_tv);
        welcomeTv.setText("Hello Tenant \\n Welcome to Rental Aid");
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();
        // FirebaseUser currentUser = mAuth.getCurrentUser();

    }
}
