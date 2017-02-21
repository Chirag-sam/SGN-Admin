package com.example.chirag.sgnadmin;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.firebase.ui.database.FirebaseIndexRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;


import static com.example.chirag.sgnadmin.categories.fromHtml;
import static java.util.Comparator.comparing;



public class DisplayProducts extends AppCompatActivity {
    private FirebaseIndexRecyclerAdapter<Product, ProductHolder> mAdapter;
    private List<Product> pli = new ArrayList<>();
    private ProductAdapter p;
    String cat;

    private ArrayList<String> cate;
    private  String[] text;

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

    @BindView(R.id.recyclerview) RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_products);
        text= getResources().getStringArray(R.array.categories);
        cate=new ArrayList<>(Arrays.asList(text));
        ButterKnife.bind(this);













        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.keepSynced(true);
        ref = mDatabase.child("products");
        Bundle b2 = getIntent().getExtras();
        cat=b2.getString("category");
        if(cat==null)
            cat="Medicines";

        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        setupadapter();










    }



    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.disptoolmenu, menu);
        final MenuItem item = menu.findItem(R.id.action_search);


        String[] cities = getResources().getStringArray(R.array.categories);
        final ArrayAdapter<String> catadapt = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, cities);












        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setQueryHint("Search Product");


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                mAdapter = new FirebaseIndexRecyclerAdapter<Product, ProductHolder>(Product.class, R.layout.productcard, ProductHolder.class, ref.orderByChild("name").startAt(newText.toUpperCase()).endAt(newText.toUpperCase()+"\uf8ff"),ref) {
                    @Override
                    public void populateViewHolder(ProductHolder ViewHolder, final Product product, int position) {
                        ViewHolder.prodname.setText(product.getName());
                        ViewHolder.prodprice.setText("₹"+product.getPrice());
                        ViewHolder.mfgname.setText(product.getCategory());
                        ViewHolder.addcart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent myIntent;
                                Bundle bundle;
                                myIntent = new Intent(DisplayProducts.this, edit.class);
                                bundle = new Bundle();
                                int to = catadapt.getPosition(product.getCategory());
                                bundle.putInt("productid",product.getPid() );
                                bundle.putString("name", product.getName());
                                bundle.putString("cat", product.getCategory());
                                bundle.putInt("catpos",to);
                                bundle.putDouble("price",product.getPrice() );
                                bundle.putInt("stock",product.getStock() );
                                bundle.putString("pic",product.getPicture() );
                                myIntent.putExtras(bundle);
                                startActivity(myIntent);

                            }

                        });

                        if (product.getPicture()!=null)
                        {   ViewHolder.prodimg.setPadding(0,0,0,0);
                            Glide.with(DisplayProducts.this).load(product.getPicture()).into(ViewHolder.prodimg);
                            int pos=cate.indexOf(product.getCategory());
                            ViewHolder.catimg.setImageResource(Imageid[pos]);


                        }
                        else {
                            int pos=cate.indexOf(product.getCategory());
                            if (pos==-1)
                                pos=0;
                            ViewHolder.prodimg.setPadding(50,50,50,50);
                            ViewHolder.prodimg.setImageResource(Imageid[pos]);
                        } }
                };

                recyclerView.setAdapter(mAdapter);


                return false;
            }
        });

        return true;
    }

    void setupadapter()
    {
        ref.orderByChild("category").equalTo(cat).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot datasnapshot) {

                for (DataSnapshot postSnapshot : datasnapshot.getChildren()) {
                    Product p = postSnapshot.getValue(Product.class);
                    if (!pli.contains(p)&&pli!=null)
                        pli.add(p);

                }
                p=new ProductAdapter(pli,DisplayProducts.this);
                recyclerView.setAdapter(p);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAdapter!=null)
            mAdapter.cleanup();

    }



}

