package com.notadeveloper.app.sgnadmin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Myorders extends AppCompatActivity {
@BindView(R.id.recycl)RecyclerView mRecyclerView;
    List<users.myorders> lc=new ArrayList<>();
    OrderAdapter o;
    LinearLayoutManager mLayoutManager;
    String x="";
   boolean b=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorders);
        ButterKnife.bind(this);
        Bundle b2=getIntent().getExtras();
        if (b2!=null)
            x=b2.getString("ordertype");

        if (x!=null){
            b = x.equals("completedorders");

        }
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setStackFromEnd(true);
        mLayoutManager.setReverseLayout(true);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        setupadapter();
    }
    void setupadapter()
    {           final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();


        mDatabase.child(x).orderByValue().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot datasnapshot) {
                lc=new ArrayList<users.myorders>();
                for (DataSnapshot postSnapshot : datasnapshot.getChildren()) {
                    users.myorders u = postSnapshot.getValue(users.myorders.class);
                    if (u!=null){
                        if (!lc.contains(u))
                            lc.add(u);}


                }
                o=new OrderAdapter(lc,Myorders.this,b);

                mRecyclerView.setAdapter(o);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
