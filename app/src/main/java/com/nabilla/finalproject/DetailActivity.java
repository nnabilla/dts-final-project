package com.nabilla.finalproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class DetailActivity extends AppCompatActivity {

    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_PRICE = "price";
    public static final String TAG_IMGURL = "imgurl";
    public static final String TAG_LATITUDE = "latitude";
    public static final String TAG_LONGITUDE = "longitude";

    private FusedLocationProviderClient fusedLocationClient;
    private String Latitude, Longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ImageView imgRoti = findViewById(R.id.iv_fotodetil);
        TextView namaRoti = findViewById(R.id.tv_namadetil);
        TextView hargaRoti = findViewById(R.id.tv_detaildetil);
        Button btn_beli = findViewById(R.id.btn_beli);

        Glide.with(this)
                .load(getIntent().getStringExtra(TAG_IMGURL)).into(imgRoti);
        namaRoti.setText(getIntent().getStringExtra(TAG_NAME));
        hargaRoti.setText(getIntent().getStringExtra(TAG_PRICE));
        //cara mendapat intnya Log.e("TAG", "onCreate: "+ Integer.parseInt(getIntent().getStringExtra(String.valueOf(HARGA_ROTI))) );
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 225);
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {

                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.


                        if (location != null) {
                            Latitude = "" + location.getLatitude();
                            Longitude = "" + location.getLongitude();
                            Log.e("TAG", "onCreate: " + Latitude + "," + Longitude);
                            // Logic to handle location object
                        }
                    }
                });


        btn_beli.setOnClickListener(view -> {
            final String idx = getIntent().getStringExtra(TAG_ID);
            final String name = getIntent().getStringExtra(TAG_NAME);
            final String price = getIntent().getStringExtra(TAG_PRICE);
            final String imgurl = getIntent().getStringExtra(TAG_IMGURL);


            Intent intent = new Intent(DetailActivity.this, BeliRotiActivity.class);
            intent.putExtra(TAG_ID, idx);
            intent.putExtra(TAG_NAME, name);
            intent.putExtra(TAG_PRICE, price);
            intent.putExtra(TAG_IMGURL, imgurl);
            intent.putExtra(TAG_LATITUDE, Latitude);
            intent.putExtra(TAG_LONGITUDE, Longitude);
            startActivity(intent);
        });
    }

}
