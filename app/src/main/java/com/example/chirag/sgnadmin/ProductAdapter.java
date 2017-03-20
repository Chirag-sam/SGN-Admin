package com.example.chirag.sgnadmin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by krsnv on 12-Feb-17.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder>  {
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
        String[] cities = mContext.getResources().getStringArray(R.array.categories);
        final ArrayAdapter<String> catadapt = new ArrayAdapter<>(mContext,
                android.R.layout.simple_dropdown_item_1line, cities);
        productHolder.addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent;
                Bundle bundle;
                myIntent = new Intent(view.getContext(), EditActivity.class);
                bundle = new Bundle();
                bundle.putInt("productid",product.getPid() );

                myIntent.putExtras(bundle);
                view.getContext().startActivity(myIntent);
            }
        });




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

    public static class ProductHolder extends RecyclerView.ViewHolder  {
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

}
