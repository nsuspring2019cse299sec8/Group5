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

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    EditText emailet, passwordet;
    Button signinbtn, signupbtn;
    TextView welcomeTv;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private String check;
    private boolean bool=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            Intent goIntent = new Intent(LoginActivity.this, OwnersActivity.class);
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
                            Toast.makeText(LoginActivity.this, "Signed up successful", Toast.LENGTH_SHORT).show();
                            Intent goHome=new Intent(LoginActivity.this,OwnersEditProfileActivity.class);
                            if (bool)
                            {
                                goHome.putExtra("owner_key","owner");
                            }
                            startActivity(goHome);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
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
                            Toast.makeText(LoginActivity.this, "Signed in successful", Toast.LENGTH_SHORT).show();
                            Intent goHome=new Intent(LoginActivity.this,OwnersActivity.class);
                            if (bool)
                            {
                                goHome.putExtra("owner_key","owner");
                            }else {
                               // goHome.putExtra("owner_key","tenant");
                            }
                            startActivity(goHome);
                            finish();

                        }else {
                            Toast.makeText(LoginActivity.this, "Signed failed!", Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }

    private void updateUI(Object o) {
        if (o==null){
            Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Successfully", Toast.LENGTH_SHORT).show();
            Intent goMain = new Intent(LoginActivity.this, MainActivity.class);
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

        mAuth = FirebaseAuth.getInstance();
        check =getIntent().getStringExtra("owner_key");
       if (check!=null){
           if (check.equals("owner")) {
               welcomeTv.setText("Welcome Owner To Rental Aid");
               bool = true;

           }
       }else{
           welcomeTv.setText("Welcome Tenant To Rental Aid");
       }
    }

    @Override
    protected void onStart() {
        super.onStart();
       // FirebaseUser currentUser = mAuth.getCurrentUser();

    }
}
