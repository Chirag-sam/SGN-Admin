package com.notadeveloper.app.sgnadmin;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created by Chirag on 15-Feb-17.
 */

public class CartAdapter extends RecyclerView.Adapter<CartHolder> {


    private List<users.cartdetails> wList;

    private Context mContext;

    private ArrayList<String> cate;
    private  String[] text={
            "CAP",
            "CAPS",
            "CREAM",
            "DROPS",
            "GEL",
            "GENERAL",
            "INJ",
            "IV FLUIDS",
            "LOTIONS",
            "NEEDLE",
            "OINTMENT",
            "PACK",
            "SOAP",
            "SPRAY",
            "SURGICAL",
            "SYP",
            "SYRUP",
            "TAB",
            "TABS",
    };

    private  int[] Imageid={
            R.drawable.ic_pills,
            R.drawable.ic_pillsx,
            R.drawable.ic_cream,
            R.drawable.ic_drops,
            R.drawable.ic_gel,
            R.drawable.ic_shopping_bag,
            R.drawable.ic_injection,
            R.drawable.ic_iv_bag,
            R.drawable.ic_lotion,
            R.drawable.ic_needle,
            R.drawable.ic_ointment,
            R.drawable.ic_shopping_bag,
            R.drawable.ic_soap,
            R.drawable.ic_spray,
            R.drawable.ic_surgical,
            R.drawable.ic_syrup,
            R.drawable.ic_syrup,
            R.drawable.ic_tablets,
            R.drawable.ic_tablets




    };

    public CartAdapter(List<users.cartdetails> wList, Context context) {
        this.wList = wList;
        mContext = context;
        cate=new ArrayList<>(Arrays.asList(text));

    }

    @Override
    public CartHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.cartcard, parent, false);

        return new CartHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final CartHolder cartHolder,final int position) {

        final users.cartdetails p = wList.get(cartHolder.getAdapterPosition());

        cartHolder.prodname.setText(p.getName());

        if (p.getPicture()!=null)
        {   cartHolder.mImageView.setPadding(0,0,0,0);
            Glide.with(mContext).load(p.getPicture()).into(cartHolder.mImageView);


        }
        else {

            cartHolder.mImageView.setPadding(10,10,10,10);
            int pos=cate.indexOf(p.getCategory());
            cartHolder.mImageView.setImageResource(Imageid[pos]);

        }
        String price=String.format(Locale.UK, "%.2f",p.getQty()*p.getPrice());
        cartHolder.rupees.setText("₹"+price);
        cartHolder.prodno.setText(p.getQty()+"  x  ₹"+p.getPrice());






    }



    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return wList.size();
    }







}
