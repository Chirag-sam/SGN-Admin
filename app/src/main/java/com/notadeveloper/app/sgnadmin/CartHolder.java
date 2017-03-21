package com.notadeveloper.app.sgnadmin;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by Chirag on 15-Feb-17.
 */

public class CartHolder extends RecyclerView.ViewHolder {

    final  TextView prodname;
    final TextView prodno;

    final TextView rupees;
    final CardView cv1;
    final ImageView mImageView;




    public CartHolder(View itemView) {
        super(itemView);
        cv1 = (CardView) itemView.findViewById(R.id.cv1);
        prodname = (TextView)itemView.findViewById(R.id.prodname);
        prodno = (TextView)itemView.findViewById(R.id.prodno);
//        remove = (Button)itemView.findViewById(R.id.remove);
//        edit = (Button)itemView.findViewById(R.id.edit);
        rupees = (TextView)itemView.findViewById(R.id.rupees);
        mImageView= (ImageView)itemView.findViewById(R.id.img);
    }






}
