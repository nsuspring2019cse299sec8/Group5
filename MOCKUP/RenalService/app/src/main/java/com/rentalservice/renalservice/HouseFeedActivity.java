package com.rentalservice.renalservice;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.rentalservice.renalservice.adapter.MyAdapter;
import com.rentalservice.renalservice.model.HouseModel;
import com.rentalservice.renalservice.viewholder.HouseFeedViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HouseFeedActivity extends AppCompatActivity {

    private static final String TAG = "HouseFeedActivity";

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private String check,user_id;


    ArrayList<HouseModel>list;
    private Button interestButton,dealButton;
    private RecyclerView recyclerView;
    LinearLayoutManager mLayoutManager;
    private FirebaseRecyclerAdapter<HouseModel,HouseFeedViewHolder>adapter;
    List<String> suggestList=new ArrayList<>();
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_feed);
        recyclerView = findViewById(R.id.house_feed_recyclerview);
        database=FirebaseDatabase.getInstance();
        myRef=database.getReference("houses").getRef();
        Log.d(TAG, "onCreate: "+myRef.getKey());

        list = new ArrayList<HouseModel>();

        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    HouseModel houseModel=dataSnapshot.getValue(HouseModel.class);
                    Log.d(TAG, "onDataChange: database value  "+houseModel.toString());
                    if(houseModel == null){
                        houseModel.setName("Blah");
                        houseModel.setHouseDesc("Blah");
                        houseModel.setHouseRent("Blah");
                        houseModel.setArea("Blah");
                    }else{
                        list.add(houseModel);
                    }

                }
                myAdapter = new MyAdapter(HouseFeedActivity.this,list);
                recyclerView.setAdapter(myAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(HouseFeedActivity.this, "oops!...something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }









}
