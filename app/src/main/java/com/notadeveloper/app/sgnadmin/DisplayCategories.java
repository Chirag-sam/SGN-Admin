package com.notadeveloper.app.sgnadmin;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mikelau.croperino.CroperinoConfig;
import com.mikelau.croperino.CroperinoFileUtil;



public class DisplayCategories extends AppCompatActivity {



    private String photoUrl;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    private StorageReference imagesRef;
    private  String[] text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        text= getResources().getStringArray(R.array.categories);

        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://sgn-pharmacy.appspot.com/");
        new CroperinoConfig("IMG_" + System.currentTimeMillis() + ".jpg", "/MikeLau/Pictures", Environment.getExternalStorageDirectory().getPath());
        CroperinoFileUtil.setupDirectory(DisplayCategories.this);



        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new GridAdapter(DisplayCategories.this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent myIntent;
                Bundle bundle;
                myIntent = new Intent(DisplayCategories.this, DisplayProducts.class);
                bundle = new Bundle();
                bundle.putString("category", text[position]);
                myIntent.putExtras(bundle);
                startActivity(myIntent);

            }
        });









    }





    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }





}

