package com.rentalservice.renalservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {
    Button AsOwnerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        AsOwnerBtn = findViewById(R.id.sign_up_as_owner_btn_id);

        AsOwnerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goIntent = new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(goIntent);
            }
        });
    }
}
