package com.example.chirag.sgnadmin;



import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chirag.sgnadmin.OrdersHolder;
import com.example.chirag.sgnadmin.R;
import com.example.chirag.sgnadmin.users;

import java.util.List;

import static com.example.chirag.sgnadmin.categories.fromHtml;

/**
 * Created by Chirag on 25-Feb-17.
 */

public class OrdersAdapter extends RecyclerView.Adapter <OrdersHolder> {


    private List<users.myorders> wList;
    private Context mContext;

    public OrdersAdapter(List<users.myorders> wList, Context context) {


        this.wList = wList;
        mContext = context;
    }
    @Override
    public OrdersHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.orderscard, parent, false);

        return new OrdersHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OrdersHolder holder, int position) {
        final users.myorders c=wList.get(holder.getAdapterPosition());
        holder.orderid.setText("Orderid");
        holder.date.setText("Date");
        holder.price.setText("price");
        holder.area.setText("Area");

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(mContext, DetailedOrder.class);
                intent.putExtra("type",c.getOrderid());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wList.size();
    }
}

