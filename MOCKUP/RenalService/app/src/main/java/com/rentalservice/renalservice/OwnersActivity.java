package com.rentalservice.renalservice;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;


import com.rentalservice.renalservice.adapter.ViewPagerAdapter;
import com.rentalservice.renalservice.ui.owners.OwnersProfileFragment;

public class OwnersActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owners_activity);


        viewPager = findViewById(R.id.pager_id);
        tabLayout = findViewById(R.id.tab_container);
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);



    }

    public void setupViewPager(ViewPager viewpager){

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OwnersProfileFragment(),"Profile");
        adapter.addFragment(new AddRentFragment(),"Add Rent");
        viewpager.setAdapter(adapter);

    }
}
