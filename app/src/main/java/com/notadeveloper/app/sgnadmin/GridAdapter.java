package com.notadeveloper.app.sgnadmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by krsnv on 09-Feb-17.
 */
public class GridAdapter extends BaseAdapter {
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
    public GridAdapter(Context c,String[] web,int[] Imageid ) {
        mContext = c;
        this.Imageid = Imageid;
        this.text = web;
        cate=new ArrayList<>(Arrays.asList(text));
    }
    public GridAdapter(Context c) {
        mContext = c;

    }


    public int getCount() {
        return text.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        View v;

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            v = LayoutInflater.from(mContext).inflate(R.layout.gridicon,null);


        } else {
            v =  convertView;
        }
        ImageView imageView = (ImageView)v.findViewById(R.id.gridimage);
        TextView textView=(TextView)v.findViewById(R.id.imageTick);
        textView.setText(text[position]);
        imageView.setImageResource(Imageid[position]);

        return v;
    }

    // references to our images

}
