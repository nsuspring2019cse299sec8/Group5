package com.rentalservice.renalservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddTenant extends AppCompatActivity {
   EditText tenantName, tenantPhone, tenantNid,tenantPrevAdd;
   Button addTenant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tenant_info);

        init();

    }

    private void init() {

        tenantName = findViewById(R.id.t_name_et);
        tenantNid = findViewById(R.id.t_nid_et);
        tenantPhone = findViewById(R.id.t_phone_et);
    }
}
