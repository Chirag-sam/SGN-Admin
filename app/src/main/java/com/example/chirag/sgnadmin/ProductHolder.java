package com.example.chirag.sgnadmin;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;

import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;



/**
 * Created by Chirag on 10-Feb-17.
 */

public class ProductHolder extends RecyclerView.ViewHolder  {
    final TextView prodprice;

    final TextView mfgname;
    final TextView prodname;
    final ImageView prodimg;
    final ImageView catimg;
    final Button addcart;




    public ProductHolder(View itemView) {
        super(itemView);


        prodprice = (TextView) itemView.findViewById(R.id.prodprice);

        mfgname = (TextView) itemView.findViewById(R.id.mfgname);
        prodname = (TextView) itemView.findViewById(R.id.prodname);
        prodimg = (ImageView) itemView.findViewById(R.id.prodimg);

        catimg=(ImageView)itemView.findViewById(R.id.catimg);
        addcart = (Button)itemView.findViewById(R.id.addcart);




    }


}
