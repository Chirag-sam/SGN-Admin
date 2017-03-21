package com.notadeveloper.app.sgnadmin;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import static com.notadeveloper.app.sgnadmin.MainActivity.fromHtml;


/**
 * Created by krsnv on 17-Feb-17.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderHolder>{
        private List<users.myorders> wList;
    private Context mContext;
boolean type=false;
    public OrderAdapter(List<users.myorders> wList, Context context,boolean type) {


        this.wList = wList;
        mContext = context;
        this.type=type;
    }

    @Override
    public OrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.myordercard, parent, false);

        return new OrderHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OrderHolder holder, int position) {
        final users.myorders c=wList.get(holder.getAdapterPosition());
        holder.orderid.setText(c.getOrderid());
        holder.date.setText(c.getOrderdate());
        if (c.getAmount() != null)
        holder.price.setText("₹ "+c.getAmount());
        else holder.price.setText("");
        holder.presc.setText("Prescribed by: " + c.getPrescribedby());
        holder.deliverby.setText("Deliver By : " + c.getDeliverby());
        holder.details.setText("Details : " + c.getName()+",\nFlat no : "+c.getAdd1()+",\nArea     : "+c.getAdd2()+",\nPin     : "+c.getAddpin()+"\nPhone  : "+c.getPhone());
        if (c.getStatus().equals("Delivered"))
            holder.status.setText(fromHtml("Status: <font color='#228B22'>"+c.getStatus()+"</font>"));
        else holder.status.setText(fromHtml("Status: <font color='#FFC107'>"+c.getStatus()+"</font>"));
        holder.cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tp;
                if (type)
                    tp="completedorders";
                else tp="pendingorders";


                if (c.getUploadedprescription() == null) {
                Intent intent= new Intent(mContext, Cart.class);
                intent.putExtra("type",c.getOrderid());
                    intent.putExtra("status",tp);
                    mContext.startActivity(intent);
                } else {
                    Intent intent = new Intent(mContext, DisplayPrescription.class);
                    intent.putExtra("type", c.getOrderid());
                    intent.putExtra("status",tp);
                    mContext.startActivity(intent);
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return wList.size();
    }
}
