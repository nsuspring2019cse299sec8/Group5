package com.rentalservice.renalservice.ui.owners;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rentalservice.renalservice.AddNewHouseActivity;
import com.rentalservice.renalservice.R;

public class OwnersProfileFragment extends Fragment {

    private ImageView ownersProfileImageView;
    private TextView ownersNameTextView;
    private TextView ownersAreaTextView;
    private TextView ownersEmailTextView;
    private TextView ownersPhoneTextView;
    private TextView totalHouseNumberTextView;
    private TextView totalTenantNumberTextView;

    private Button addNewHouseButton, editProfileButton;




    public static OwnersProfileFragment newInstance() {
        return new OwnersProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.owners_profile_fragment, container, false);
        init_view(v);
        addNewHouseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent(getContext(), AddNewHouseActivity.class);
                startActivity(add);
            }
        });
        return v;
    }

    private void init_view(View v) {
        ownersProfileImageView = v.findViewById(R.id.owners_profile_imageview);
        ownersNameTextView = v.findViewById(R.id.owners_name_textview);
        ownersEmailTextView = v.findViewById(R.id.owners_email_textview);
        ownersPhoneTextView = v.findViewById(R.id.owners_phone_textview);
        ownersAreaTextView = v.findViewById(R.id.owners_home_textview);
        totalHouseNumberTextView = v.findViewById(R.id.total_house_number_textview);
        totalTenantNumberTextView = v.findViewById(R.id.total_tenants_number_textview);
        addNewHouseButton = v.findViewById(R.id.add_house_btn_id);

    }


}
