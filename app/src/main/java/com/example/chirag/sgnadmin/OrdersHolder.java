package com.example.chirag.sgnadmin;



import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.chirag.sgnadmin.R;


/**
 * Created by Chirag on 25-Feb-17.
 */

public class OrdersHolder extends RecyclerView.ViewHolder {
    TextView date;
    TextView time;
    TextView area;
    TextView tap;
    TextView orderid;
    TextView price;
    CardView cv;
    CheckBox status;
    public OrdersHolder(View itemView) {
        super(itemView);
        date = (TextView)itemView.findViewById(R.id.date);
        time = (TextView)itemView.findViewById(R.id.time);
        area = (TextView)itemView.findViewById(R.id.area);
        orderid = (TextView)itemView.findViewById(R.id.orderid);
        tap = (TextView)itemView.findViewById(R.id.tap);
        price = (TextView)itemView.findViewById(R.id.price);
        cv = (CardView)itemView.findViewById(R.id.cv);
        status = (CheckBox)itemView.findViewById(R.id.status);


    }
}
