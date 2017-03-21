package com.notadeveloper.app.sgnadmin;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by krsnv on 17-Feb-17.
 */

public class OrderHolder extends RecyclerView.ViewHolder {
    final TextView orderid;
    final TextView date;
    final TextView price;
    final TextView status;
    final TextView presc;
    final TextView deliverby;
    final TextView details;
    final CardView cv1;

    public OrderHolder(View itemView) {
        super(itemView);
        cv1 = (CardView) itemView.findViewById(R.id.cv1);
        orderid = (TextView)itemView.findViewById(R.id.orderid);
        date = (TextView)itemView.findViewById(R.id.date);
        price = (TextView)itemView.findViewById(R.id.orderprice);
        status= (TextView) itemView.findViewById(R.id.orderstatus);
        presc= (TextView) itemView.findViewById(R.id.prescript);
        deliverby = (TextView) itemView.findViewById(R.id.deliverby);
        details = (TextView) itemView.findViewById(R.id.details);
    }
}
