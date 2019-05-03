package com.rentalservice.renalservice.ui.owners;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rentalservice.renalservice.AddNewHouseActivity;
import com.rentalservice.renalservice.Owner;
import com.rentalservice.renalservice.R;

public class OwnersProfileFragment extends Fragment {
    private static final String TAG = "OwnersProfileFragment";
    private ImageView ownersProfileImageView;
    private TextView ownersNameTextView;
    private TextView ownersAreaTextView;
    private TextView ownersEmailTextView;
    private TextView ownersPhoneTextView;
    private TextView totalHouseNumberTextView;
    private TextView totalTenantNumberTextView;

    private Button addNewHouseButton, editProfileButton, logoutButton;

    FirebaseDatabase database;
    DatabaseReference myRef;

    FirebaseUser mUser;
    FirebaseAuth mAuth;

    private String user_id;

    public OwnersProfileFragment() {
    }

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
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getContext(), "Sign out Success!", Toast.LENGTH_SHORT).show();
            }
        });

        // Read from the database
        myRef.child(user_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Owner owner = dataSnapshot.getValue(Owner.class);
                //Log.d(TAG, "Value is: " + owner.toString());
                ownersNameTextView.setText(owner.getName());
                ownersEmailTextView.setText(owner.getEmail());
                ownersPhoneTextView.setText(owner.getPhone());
                ownersAreaTextView.setText(owner.getArea());
                totalHouseNumberTextView.setText(owner.getTotal_house());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
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
        logoutButton = v.findViewById(R.id.logout_button);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("owners");

        mAuth =FirebaseAuth.getInstance();
        mUser =mAuth.getCurrentUser();
        try{
            user_id =mUser.getUid();
            Log.d(TAG, "init: check str: ");
        }catch (NullPointerException e){
            Log.d(TAG, "init: "+e.getMessage());
        }


    }


}
