package com.rentalservice.renalservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {
    Button AsOwnerBtn;
    Button asTenantBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        AsOwnerBtn = findViewById(R.id.as_owner_btn);
        asTenantBtn = findViewById(R.id.as_tenant_btn);

        AsOwnerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goIntent = new Intent(WelcomeActivity.this,LoginActivity.class);
                goIntent.putExtra("owner_key","owner");
                startActivity(goIntent);
            }
        });

        asTenantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goIntent = new Intent(WelcomeActivity.this,LoginForTenantActivity.class);
                startActivity(goIntent);
            }
        });
    }
}
