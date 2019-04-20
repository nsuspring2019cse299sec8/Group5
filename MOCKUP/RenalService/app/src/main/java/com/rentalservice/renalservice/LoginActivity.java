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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    EditText emailet,passwordet;
    Button submitBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailet.getText().toString();
                String password = passwordet.getText().toString();

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){

                    mAuth.signInWithEmailAndPassword(email,password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(LoginActivity.this, "Authentication succss", Toast.LENGTH_SHORT).show();
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        updateUI(user);
                                    }else {
                                        updateUI(null);
                                    }

                                }
                            });
                }else{
                    Toast.makeText(LoginActivity.this, "Fields are empty not allowed", Toast.LENGTH_SHORT).show();
                }



            }
        });
    }

    private void updateUI(Object o) {
        if (o==null){
            Toast.makeText(this, "User not found!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Sign in Successfully", Toast.LENGTH_SHORT).show();
            Intent goMain = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(goMain);
            finish();

        }
    }

    private void init() {
        emailet = findViewById(R.id.email_et_id);
        passwordet = findViewById(R.id.pass_et_id);
        submitBtn = findViewById(R.id.sign_in_as_owner_btn_id);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
       // FirebaseUser currentUser = mAuth.getCurrentUser();

    }
}
