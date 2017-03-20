package com.example.chirag.sgnadmin;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mikelau.croperino.Croperino;
import com.mikelau.croperino.CroperinoConfig;
import com.mikelau.croperino.CroperinoFileUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class EditActivity extends AppCompatActivity {

    private ImageButton mProfile;
    private String photoUrl;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    private StorageReference imagesRef;

    private DatabaseReference ref;
    private Product p=new Product();
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        mProfile = (ImageButton) findViewById(R.id.picture);
        final TextInputLayout idlt = (TextInputLayout)findViewById(R.id.idlt);
        final EditText id=(EditText)findViewById(R.id.id);
        final TextInputLayout namelt = (TextInputLayout)findViewById(R.id.namelt);
        final EditText name=(EditText)findViewById(R.id.name);
        final TextInputLayout catlt = (TextInputLayout)findViewById(R.id.catlt);
        final Spinner cat=(Spinner)findViewById(R.id.cat);
//        String[] cities = getResources().getStringArray(R.array.categories);
//        final ArrayList<String> categories=new ArrayList<>(Arrays.asList(cities));
        final ArrayAdapter<String> catadapt = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, text);
        cat.setAdapter(catadapt);
        cate=new ArrayList<>(Arrays.asList(text));
        final TextInputLayout pricelt = (TextInputLayout)findViewById(R.id.pricelt);
        final EditText price=(EditText)findViewById(R.id.price);

        final TextInputLayout stocklt = (TextInputLayout)findViewById(R.id.stocklt);
        final EditText stock=(EditText)findViewById(R.id.stock);









        Bundle b2 = getIntent().getExtras();

        final int prdidb=b2.getInt("productid");


        //FirebaseUser userau = FirebaseAuth.getInstance().getCurrentUser();
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://sgn-pharmacy.appspot.com/");
        ref= FirebaseDatabase.getInstance().getReference();
        ref.child("products").child(String.valueOf(prdidb)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                p=dataSnapshot.getValue(Product.class);
                id.setText(String.valueOf(p.getPid()));
                name.setText(p.getName());
                price.setText(String.valueOf(p.getPrice()));
                stock.setText(String.valueOf(p.getStock()));
                cat.setSelection(cate.indexOf(p.getCategory()));
                if (p.getPicture()!=null)
                {   mProfile.setPadding(0,0,0,0);
                    Glide.with(EditActivity.this).load(p.getPicture()).into(mProfile);
                    int pos=cate.indexOf(p.getCategory());
                    mProfile.setImageResource(Imageid[pos]);
                }
                else {


                    int pos=cate.indexOf(p.getCategory());
                    mProfile.setPadding(50,50,50,50);
                    mProfile.setImageResource(Imageid[pos]);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






        new CroperinoConfig("IMG_" + System.currentTimeMillis() + ".jpg", "/MikeLau/Pictures", Environment.getExternalStorageDirectory().getPath());
        CroperinoFileUtil.setupDirectory(EditActivity.this);







        Button proceed=(Button)findViewById(R.id.proceed);
        mProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String a = id.getText().toString();
                if (TextUtils.isEmpty(a))
                {
                    Snackbar.make(findViewById(android.R.id.content), "Enter Product id First", Snackbar.LENGTH_SHORT).show();

                }
                else {
                    imagesRef = storageRef.child("images").child("picture").child(a);
                    if (CroperinoFileUtil.verifyStoragePermissions(EditActivity.this)) {
                        prepareChooser();
                    }}
            }
        });
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean cancel = false;
                final String a = id.getText().toString();
                final String b = name.getText().toString();
                final String c = cat.getSelectedItem().toString();
                final String f = price.getText().toString();
                final String h = stock.getText().toString();
                View focusView = null;

                if (TextUtils.isEmpty(a))
                {
                    idlt.setError("Invalid Id");
                    focusView = idlt;
                    cancel = true;
                }
                else idlt.setError(null);
                if (TextUtils.isEmpty(b))
                {
                    namelt.setError("Invalid Name");
                    focusView = namelt;
                    cancel = true;
                }
                else namelt.setError(null);
                if (TextUtils.isEmpty(c))
                {
                    catlt.setError("Invalid Category");
                    focusView = catlt;
                    cancel = true;
                }
                else catlt.setError(null);

                if (TextUtils.isEmpty(f))
                {
                    pricelt.setError("Invalid Price");
                    focusView = pricelt;
                    cancel = true;
                }
                else pricelt.setError(null);
                if (TextUtils.isEmpty(h))
                {
                    stocklt.setError("Invalid Stock");
                    focusView = stocklt;
                    cancel = true;
                }
                else stocklt.setError(null);
                if (cancel) {
                    // There was an error; don't attempt login and focus the first
                    // form field with an error.
                    focusView.requestFocus();
                } else {
                    DatabaseReference mDatabase = ref.child("products");
                    Product px=new Product(Integer.parseInt(a),b,c,Double.parseDouble(f),Integer.parseInt(h),null);
                    ref.child("products").child(String.valueOf(prdidb)).setValue(null);
                    mDatabase.child(a).setValue(px);

                    finish();



                }


            }
        });

    }
    private void prepareChooser() {
        Croperino.prepareChooser(EditActivity.this, "Change Picture", ContextCompat.getColor(EditActivity.this, android.R.color.background_dark));
    }

    private void prepareCamera() {
        Croperino.prepareCamera(EditActivity.this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case CroperinoConfig.REQUEST_TAKE_PHOTO:
                if (resultCode == Activity.RESULT_OK) {
                    Croperino.runCropImage(CroperinoFileUtil.getmFileTemp(), EditActivity.this, true, 1, 1, 0, 0);
                }
                break;
            case CroperinoConfig.REQUEST_PICK_FILE:
                if (resultCode == Activity.RESULT_OK) {
                    CroperinoFileUtil.newGalleryFile(data, EditActivity.this);
                    Croperino.runCropImage(CroperinoFileUtil.getmFileTemp(), EditActivity.this, true, 1, 1, 0, 0);
                }
                break;
            case CroperinoConfig.REQUEST_CROP_PHOTO:
                if (resultCode == Activity.RESULT_OK) {
                    Uri i = Uri.fromFile(CroperinoFileUtil.getmFileTemp());
                    mProfile.setImageURI(i);
                    UploadTask uploadTask = imagesRef.putFile(i);

// Register observers to listen for when the download is done or if it fails
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                            Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            photoUrl = downloadUrl.toString();
                        }
                    });
                    //Do saving / uploading of photo method here.
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CroperinoFileUtil.REQUEST_CAMERA) {
            for (int i = 0; i < permissions.length; i++) {
                String permission = permissions[i];
                int grantResult = grantResults[i];

                if (permission.equals(android.Manifest.permission.CAMERA)) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        prepareCamera();
                    }
                }
            }
        } else if (requestCode == CroperinoFileUtil.REQUEST_EXTERNAL_STORAGE) {
            boolean wasReadGranted = false;
            boolean wasWriteGranted = false;

            for (int i = 0; i < permissions.length; i++) {
                String permission = permissions[i];
                int grantResult = grantResults[i];

                if (permission.equals(android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        wasReadGranted = true;
                    }
                }
                if (permission.equals(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        wasWriteGranted = true;
                    }
                }
            }

            if (wasReadGranted && wasWriteGranted) {
                prepareChooser();
            }
        }
    }
}
