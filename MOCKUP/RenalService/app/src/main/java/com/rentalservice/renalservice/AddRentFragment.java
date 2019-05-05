package com.rentalservice.renalservice;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddRentFragment extends Fragment {
    Spinner selectSP;
    EditText amount_et;
    Button addBtn;
    FirebaseUser mUser;
    FirebaseAuth mAuth;
    String user_id;


    public AddRentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_add_rent, container, false);
        addBtn =v.findViewById(R.id.add_amount_btn);
        selectSP = v.findViewById(R.id.select_house_id);
        amount_et = v.findViewById(R.id.amount_edittext);

        mAuth =FirebaseAuth.getInstance();
        mUser =mAuth.getCurrentUser();
        user_id = mUser.getUid();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("houses");

                ref.child(user_id).child("houseRent").setValue(amount_et.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getActivity(), "House Rent Updated", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });
        return v;
    }

}
