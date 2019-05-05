package com.rentalservice.renalservice.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rentalservice.renalservice.R;
import com.rentalservice.renalservice.model.HouseModel;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;
    ArrayList<HouseModel> models;

    public MyAdapter(Context context, ArrayList<HouseModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).
                inflate(R.layout.home_feed_list,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.ownerNameTextview.setText(models.get(i).getName());
        myViewHolder.houseDescTextview.setText(models.get(i).getHouseDesc());
        myViewHolder.houseRentTextview.setText(models.get(i).getHouseRent());
        myViewHolder.houseAreaTextview.setText(models.get(i).getArea());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView ownerNameTextview, houseDescTextview;
        private TextView houseRentTextview, houseAreaTextview;
        public Button interestButton,dealButton;

        public MyViewHolder(@NonNull View mView) {
            super(mView);
            ownerNameTextview = mView.findViewById(R.id.o_name_textview);
            houseDescTextview = mView.findViewById(R.id.desc_textview);
            houseRentTextview = mView.findViewById(R.id.house_rent_textview);
            houseAreaTextview = mView.findViewById(R.id.house_area_textview);
            interestButton =mView.findViewById(R.id.interest_button);
            dealButton =mView.findViewById(R.id.deal_button);
        }
    }
}
