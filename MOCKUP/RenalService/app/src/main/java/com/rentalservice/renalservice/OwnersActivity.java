package com.rentalservice.renalservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rentalservice.renalservice.ui.owners.OwnersProfileFragment;

public class OwnersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owners_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, OwnersProfileFragment.newInstance())
                    .commitNow();
        }
    }
}
