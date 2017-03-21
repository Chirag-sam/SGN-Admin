package com.notadeveloper.app.sgnadmin;

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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mikelau.croperino.Croperino;
import com.mikelau.croperino.CroperinoConfig;
import com.mikelau.croperino.CroperinoFileUtil;
public class AddProductActivity extends AppCompatActivity {

    private ImageButton mProfile;
    private String photoUrl;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    private StorageReference imagesRef;

    private DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //FirebaseUser userau = FirebaseAuth.getInstance().getCurrentUser();
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://sgn-pharmacy.appspot.com/");
        ref= FirebaseDatabase.getInstance().getReference();



        new CroperinoConfig("IMG_" + System.currentTimeMillis() + ".jpg", "/MikeLau/Pictures", Environment.getExternalStorageDirectory().getPath());
        CroperinoFileUtil.setupDirectory(AddProductActivity.this);

        mProfile = (ImageButton) findViewById(R.id.picture);
        final TextInputLayout idlt = (TextInputLayout)findViewById(R.id.idlt);
        final EditText id=(EditText)findViewById(R.id.id);
        final TextInputLayout namelt = (TextInputLayout)findViewById(R.id.namelt);
        final EditText name=(EditText)findViewById(R.id.name);
        final TextInputLayout catlt = (TextInputLayout)findViewById(R.id.catlt);
        final Spinner cat=(Spinner)findViewById(R.id.cat);
        String[] cities = getResources().getStringArray(R.array.categories);
        final ArrayAdapter<String> catadapt = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, cities);
        cat.setAdapter(catadapt);

        final TextInputLayout pricelt = (TextInputLayout)findViewById(R.id.pricelt);
        final EditText price=(EditText)findViewById(R.id.price);

        final TextInputLayout stocklt = (TextInputLayout)findViewById(R.id.stocklt);
        final EditText stock=(EditText)findViewById(R.id.stock);




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
                    if (CroperinoFileUtil.verifyStoragePermissions(AddProductActivity.this)) {
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
                    DatabaseReference mDatabase = ref.child("products").child(a);
                    Product p = new Product(Integer.parseInt(a), b, c,Double.parseDouble(f),Integer.parseInt(h),photoUrl);
                    mDatabase.setValue(p);
                    Intent myIntent = new Intent(AddProductActivity.this, MainActivity.class);
                    startActivity(myIntent);
                    finish();


                }


            }
        });

    }
    private void prepareChooser() {
        Croperino.prepareChooser(AddProductActivity.this, "Change Picture", ContextCompat.getColor(AddProductActivity.this, android.R.color.background_dark));
    }

    private void prepareCamera() {
        Croperino.prepareCamera(AddProductActivity.this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case CroperinoConfig.REQUEST_TAKE_PHOTO:
                if (resultCode == Activity.RESULT_OK) {
                    Croperino.runCropImage(CroperinoFileUtil.getmFileTemp(), AddProductActivity.this, true, 1, 1, 0, 0);
                }
                break;
            case CroperinoConfig.REQUEST_PICK_FILE:
                if (resultCode == Activity.RESULT_OK) {
                    CroperinoFileUtil.newGalleryFile(data, AddProductActivity.this);
                    Croperino.runCropImage(CroperinoFileUtil.getmFileTemp(), AddProductActivity.this, true, 1, 1, 0, 0);
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
