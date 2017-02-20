package com.example.chirag.sgnadmin;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by krsnv on 12-Feb-17.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductHolder>  {
    private List<Product> wList;
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
            R.drawable.ic_pillsx,
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
            R.drawable.ic_shopping_bag,
            R.drawable.ic_spray,
            R.drawable.ic_surgical,
            R.drawable.ic_syrup,
            R.drawable.ic_syrup,
            R.drawable.ic_tablets,
            R.drawable.ic_tablets




    };

    public ProductAdapter(List<Product> List, Context context) {
        this.wList = List;
        mContext = context;
        cate=new ArrayList<>(Arrays.asList(text));
    }
    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.productcard, parent, false);

        return new ProductHolder(itemView);
    }
    @Override
    public void onBindViewHolder(ProductHolder productHolder, int position) {
        final Product product = wList.get(position);
        productHolder.prodname.setText(product.getName());
        productHolder.prodprice.setText("₹"+product.getPrice());
        productHolder.mfgname.setText(product.getCategory());




        if (product.getPicture()!=null)
        {   productHolder.prodimg.setPadding(0,0,0,0);
            Glide.with(mContext).load(product.getPicture()).into(productHolder.prodimg);
            int pos=cate.indexOf(product.getCategory());
            productHolder.catimg.setImageResource(Imageid[pos]);
        }
        else {


            int pos=cate.indexOf(product.getCategory());
            productHolder.prodimg.setPadding(50,50,50,50);
            productHolder.prodimg.setImageResource(Imageid[pos]);

        }

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
