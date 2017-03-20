package com.example.chirag.sgnadmin;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Chirag on 25-Feb-17.
 */

public class DetailedOrderAdapter extends RecyclerView.Adapter <DetailedOrderAdapter.DetailedOrderHolder> {
    @Override
    public DetailedOrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(DetailedOrderHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public static class DetailedOrderHolder extends RecyclerView.ViewHolder {
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
}
