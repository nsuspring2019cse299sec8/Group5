package com.rentalservice.renalservice.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rentalservice.renalservice.R;

public class HouseFeedViewHolder extends RecyclerView.ViewHolder{
    private static final String TAG = "HouseFeedViewHolder";

    public LinearLayout root;
     TextView ownerNameTextview, houseDescTextview;
     TextView houseRentTextview, houseAreaTextview;
     Button interestButton,dealButton;
   // private ItemClickListner itemClickListner;
    View mView;

    public HouseFeedViewHolder(View mView) {
        super(mView);
        this.mView = mView;

        //Views
        ownerNameTextview = mView.findViewById(R.id.owners_name_textview);
        houseDescTextview = mView.findViewById(R.id.desc_textview);
        houseRentTextview = mView.findViewById(R.id.house_rent_textview);
        houseAreaTextview = mView.findViewById(R.id.house_area_textview);
        interestButton =mView.findViewById(R.id.interest_button);
        dealButton =mView.findViewById(R.id.deal_button);

        //item click
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });
        //item long click
        mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view, getAdapterPosition());
                return true;
            }
        });

    }

    //set details to recycler view row
    public void setDetails(String name, String desc, String rent, String area){

       // Picasso.get().load(image).into(mImageIv);
        ownerNameTextview.setText(name);
        houseAreaTextview.setText(area);
        houseDescTextview.setText(desc);
        houseRentTextview.setText(rent);
        Log.d(TAG, "setDetails: name: "+name);
        Log.d(TAG, "setDetails: area: "+area);
        Log.d(TAG, "setDetails: dsc: "+desc);
    }

    private HouseFeedViewHolder.ClickListener mClickListener;

    //interface to send callbacks
    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View  view, int position);
    }

    public void setOnClickListener(HouseFeedViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }
}
