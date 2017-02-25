package com.example.chirag.sgnadmin;



import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chirag.sgnadmin.R;


/**
 * Created by Chirag on 25-Feb-17.
 */

public class DetailedOrderHolder extends RecyclerView.ViewHolder {
    ImageView catimg;
    ImageView prodimg;
    TextView prodname;
    TextView numberofotems;
    TextView prodprice;
    public DetailedOrderHolder(View itemView) {
        super(itemView);
        prodname = (TextView)itemView.findViewById(R.id.prodname);
        prodprice = (TextView)itemView.findViewById(R.id.prodprice);
        numberofotems = (TextView)itemView.findViewById(R.id.numberofitems);
        catimg = (ImageView)itemView.findViewById(R.id.catimg);
        prodimg = (ImageView)itemView.findViewById(R.id.prodimg);


    }
}

