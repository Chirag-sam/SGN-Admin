package com.notadeveloper.app.sgnadmin;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import com.bumptech.glide.Glide;

import com.firebase.ui.database.FirebaseIndexRecyclerAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;


import static java.util.Comparator.comparing;



public class DisplayProducts extends AppCompatActivity {
    private FirebaseIndexRecyclerAdapter<Product, ProductAdapter.ProductHolder> mAdapter;
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

                mAdapter = new FirebaseIndexRecyclerAdapter<Product,ProductAdapter.ProductHolder>(Product.class, R.layout.productcard, ProductAdapter.ProductHolder.class, ref.orderByChild("name").startAt(newText.toUpperCase()).endAt(newText.toUpperCase()+"\uf8ff"),ref) {
                    @Override
                    public void populateViewHolder(ProductAdapter.ProductHolder ViewHolder, final Product product, int position) {
                        ViewHolder.prodname.setText(product.getName());
                        ViewHolder.prodprice.setText("₹"+product.getPrice());
                        ViewHolder.mfgname.setText(product.getCategory());
                        ViewHolder.addcart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

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

