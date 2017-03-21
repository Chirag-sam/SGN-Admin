package com.notadeveloper.app.sgnadmin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DisplayPrescription extends AppCompatActivity {
    String x = "";
    @BindView(R.id.prescimg)
    ImageView mPrescimg;
    @BindView(R.id.vieworder)
    Button mVieworder;
    String ordid;
    String stattpe;
    @OnClick(R.id.vieworder)
    void vieworder() {
        Intent intent = new Intent(DisplayPrescription.this, Cart.class);
        intent.putExtra("type", x);
        startActivity(intent);
    }

    users.myorders u = new users.myorders();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_prescription);
        ButterKnife.bind(this);

        Bundle b = getIntent().getExtras();
        if (b != null)
        {   ordid = b.getString("type");
        stattpe=b.getString("status");}
        Log.e("sdfvcb", "onCreate: "+ordid+stattpe );


            final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child(stattpe).child(ordid);

            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    u = dataSnapshot.getValue(users.myorders.class);

                        Log.e("xxcz", "onDataChange: " + u.getUid());
                        Glide.with(DisplayPrescription.this).load(u.getUploadedprescription()).thumbnail(0.3f).into(mPrescimg);
                        if (u.getItems() == null)
                            mVieworder.setVisibility(View.GONE);
                        else mVieworder.setVisibility(View.VISIBLE);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

}
