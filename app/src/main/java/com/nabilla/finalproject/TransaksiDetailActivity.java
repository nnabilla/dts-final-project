package com.nabilla.finalproject;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class TransaksiDetailActivity extends AppCompatActivity implements OnMapReadyCallback {
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_PRICE = "price";
    public static final String TAG_IMGURL = "imgurl";
    public static final String TAG_PEMESAN = "pemesan";
    public static final String TAG_LATITUDE = "latitude";
    public static final String TAG_LONGITUDE = "longitude";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi_detail);
        ImageView detilFoto = findViewById(R.id.iv_transaksifoto);
        TextView detilPemesan = findViewById(R.id.tv_transaksipemesan);
        TextView detilNama = findViewById(R.id.tv_transaksinama);
        TextView detilHarga = findViewById(R.id.tv_transaksiharga);

        Glide.with(this)
                .load(getIntent().getStringExtra(TAG_IMGURL)).into(detilFoto);
        detilPemesan.setText(getIntent().getStringExtra(TAG_PEMESAN));
        detilNama.setText(getIntent().getStringExtra(TAG_NAME));
        detilHarga.setText(getIntent().getStringExtra(TAG_PRICE));
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        float zoomLevel = 15.0f;


        double latpos = Double.parseDouble(getIntent().getStringExtra(TAG_LATITUDE));
        double Longpos = Double.parseDouble(getIntent().getStringExtra(TAG_LONGITUDE));
        LatLng posisi = new LatLng(latpos, Longpos);
        googleMap.addMarker(new MarkerOptions()
                .position(posisi)
                .title("Posisi " + getIntent().getStringExtra(TAG_PEMESAN)));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posisi, zoomLevel));
    }
}
