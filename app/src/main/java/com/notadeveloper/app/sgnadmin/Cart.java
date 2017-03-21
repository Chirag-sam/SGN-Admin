package com.notadeveloper.app.sgnadmin;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseIndexRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Cart extends AppCompatActivity {
    @BindView(R.id.recyc)
    RecyclerView mRecyclerView;
    @BindView(R.id.pricetext13)
    TextView totprice;
    @BindView(R.id.orderstatus)
    Button mOrderstatus;
    @BindView(R.id.cancelorder)
    Button mCancelorder;


    private FirebaseIndexRecyclerAdapter<Product, ProductAdapter.ProductHolder> mAdapter;
    private List<users.cartdetails> pli = new ArrayList<>();
    private CartAdapter p;
    String cat;
    double sum;
    private long estimatedServerTimeMs;
    private ArrayList<users.mycart> lc;
    private DatabaseReference mDatabase;
    private DatabaseReference mdb;
    private DrawerLayout dl;
    private boolean cancel;
    private users u;
    private boolean prescpresent;
    private ActionBarDrawerToggle abdt;
    users.myorders mo=new users.myorders();
    private String[] text = {
            "Medicines",
            "First Aid",
            "General",
            "Baby Products"
    };
    private int[] Imageid = {
            R.drawable.drugs,
            R.drawable.firstaid,
            R.drawable.ic_shopping_bag,
            R.drawable.baby};
    String ordid;
    String stattpe;
    private RecyclerView.LayoutManager mLayoutManager;

    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Bundle b = getIntent().getExtras();
        ButterKnife.bind(this);
        if (b != null) {
            ordid = b.getString("type");
            stattpe=b.getString("status");
            assert stattpe != null;


        }
       if (stattpe.equals("completedorders"))
       {
           mCancelorder.setVisibility(View.GONE);
           mOrderstatus.setVisibility(View.GONE);
       }
       else {
           mCancelorder.setVisibility(View.VISIBLE);
           mOrderstatus.setVisibility(View.VISIBLE);
       }






        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        setupadapter();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mOrderstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child(stattpe).child(ordid).setValue(null);
                mo.setStatus("Delivered");
                mDatabase.child("users").child(mo.getUid()).child("myorders").child(mo.getOrderid()).setValue(mo);

                mDatabase.child("completedorders").child(ordid).setValue(mo);
                finish();
            }
        });
        mCancelorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDatabase.child(stattpe).child(ordid).setValue(null);
                mo.setStatus("Canceled");
                mDatabase.child("users").child(mo.getUid()).child("myorders").child(mo.getOrderid()).setValue(mo);

                mDatabase.child("completedorders").child(ordid).setValue(mo);
                finish();
            }
        });


    }

    void setupadapter() {
        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();



            ref = mDatabase.child(stattpe).child(ordid);


        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot datasnapshot) {



                    mo = datasnapshot.getValue(users.myorders.class);
                    lc = mo.getItems();



                if (lc != null) {
                    sum = 0;

                    pli = new ArrayList<users.cartdetails>();
                    for (final users.mycart cart : lc) {
                        sum += cart.getAmt();
                        mDatabase.child("products").child(String.valueOf(cart.getPid())).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Product pr = dataSnapshot.getValue(Product.class);
                                users.cartdetails cd = new users.cartdetails(pr.getPid(), pr.name, pr.getCategory(), cart.getQty(), cart.getAmt(), pr.getPicture(), pr.getPrice());

                                pli.add(cd);
                                p = new CartAdapter(pli, Cart.this);
                                mRecyclerView.setAdapter(p);
                                //noinspection DebugLogging
                                Log.d("xz", "onDataChange: " + pr.getPid());


                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        totprice.setText("Price: ₹" + sum);
                    }


                } else {
                    totprice.setText("Nothing to show!");

                    pli = new ArrayList<users.cartdetails>();
                    lc = new ArrayList<users.mycart>();

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private boolean isEditTextEmpty(String mInput) {
        return mInput.length() == 0;
    }


}

