package com.rentalservice.renalservice;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rentalservice.renalservice.model.HouseModel;

public class AddNewHouseActivity extends AppCompatActivity {
    private static final String TAG = "AddNewHouseActivity";

    TextInputEditText houseNameInputEt,flatNameInputEt,roadNameInputEt,blockNameInputEt;
    TextInputLayout houseNameInput,flatNameInput,roadNameInput,blockNameInput,rentInput;
    Button submitButton;
    RadioGroup areaGroupButton;
    RadioButton basundaraBtn, baridaraBtn, gulsanBtn;

    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser mUser;
    FirebaseAuth mAuth;

    private String user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_house);

        init();


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_id= mUser.getUid();
                String area ="";

                switch (areaGroupButton.getCheckedRadioButtonId()){

                    case R.id.baridara_group_btn:
                        area="baridara";
                        break;
                    case R.id.basundara_group_btn:
                        area="basundara";
                        break;
                    case R.id.gulsan_group_btn:
                        area="gulsan";
                        break;
                }
                HouseModel houseModel = new HouseModel(houseNameInput.getEditText().getText().toString(),
                         roadNameInput.getEditText().getText().toString(),
                        flatNameInput.getEditText().getText().toString(),
                        blockNameInput.getEditText().getText().toString(),
                        rentInput.getEditText().getText().toString(),area
                        );
                myRef.child(user_id).setValue(houseModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(AddNewHouseActivity.this, "New House Updated", Toast.LENGTH_SHORT).show();
                            DatabaseReference addHouse= database.getReference("owners");
                            
                            Intent goIntent = new Intent(AddNewHouseActivity.this, OwnersActivity.class);
                            startActivity(goIntent);
                            finish();

                        }else{
                            Toast.makeText(AddNewHouseActivity.this, "House  Update Fail!", Toast.LENGTH_SHORT).show();

                        }
                    }
                });




                //myRef.child(user_id)
            }
        });
    }

    private void init() {
        submitButton = findViewById(R.id.submit_button);
        houseNameInput = findViewById(R.id.textInputLayout);
        roadNameInput = findViewById(R.id.textInputLayout3);
        flatNameInput = findViewById(R.id.textInputLayout2);
        blockNameInput = findViewById(R.id.textInputLayout4);
        rentInput = findViewById(R.id.textInputLayout6);
        areaGroupButton =findViewById(R.id.area_group_btn_id);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("houses");

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
