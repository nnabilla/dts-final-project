package com.nabilla.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.nabilla.finalproject.adapter.adapterTransaksi;
import com.nabilla.finalproject.database.DBHelper;
import com.nabilla.finalproject.model.TransaksiModel;

public class TransaksiLogActivity extends AppCompatActivity {
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_PRICE = "price";
    public static final String TAG_IMGURL = "imgurl";
    public static final String TAG_PEMESAN = "pemesan";
    public static final String TAG_LATITUDE = "latitude";
    public static final String TAG_LONGITUDE = "longitude";
    ListView listView;
    AlertDialog.Builder dialog;
    List<TransaksiModel> itemList = new ArrayList<>();
    adapterTransaksi adapter;
    DBHelper SQLite = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi_log);
        //Tambah SQLite
        SQLite = new DBHelper(getApplicationContext());

        //Tambah List View
        listView = findViewById(R.id.list_view);

        adapter = new adapterTransaksi(TransaksiLogActivity.this, itemList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            final String idx = itemList.get(i).getId();
            final String name = itemList.get(i).getNama();
            final String price = itemList.get(i).getHarga();
            final String imgurl = itemList.get(i).getImgUrl();
            final String pemesan = itemList.get(i).getPemesan();
            final String lat = itemList.get(i).getLatitude();
            final String Long = itemList.get(i).getLongitude();

            Intent intent = new Intent(TransaksiLogActivity.this, TransaksiDetailActivity.class);
            intent.putExtra(TAG_ID, idx);
            intent.putExtra(TAG_NAME, name);
            intent.putExtra(TAG_PRICE, price);
            intent.putExtra(TAG_IMGURL, imgurl);
            intent.putExtra(TAG_PEMESAN, pemesan);
            intent.putExtra(TAG_LATITUDE, lat);
            intent.putExtra(TAG_LONGITUDE, Long);
            startActivity(intent);

        });


        getAllData();
    }

    void getAllData() {
        ArrayList<HashMap<String, String>> row = SQLite.getAllTransaksiData();

        for (int i = 0; i < row.size(); i++) {
            String id = row.get(i).get(TAG_ID);
            String nama = row.get(i).get(TAG_NAME);
            String pemesan = row.get(i).get(TAG_PEMESAN);
            String price = row.get(i).get(TAG_PRICE);
            String url = row.get(i).get(TAG_IMGURL);
            String lat = row.get(i).get(TAG_LATITUDE);
            String Long = row.get(i).get(TAG_LONGITUDE);

            TransaksiModel transaksiModel = new TransaksiModel();

            transaksiModel.setId(id);
            transaksiModel.setNama(nama);
            transaksiModel.setHarga(price);
            transaksiModel.setImgUrl(url);
            transaksiModel.setLatitude(lat);
            transaksiModel.setLongitude(Long);
            transaksiModel.setPemesan(pemesan);


            itemList.add(transaksiModel);
        }
        adapter.notifyDataSetChanged();

    }

    protected void onResume() {
        super.onResume();
        itemList.clear();
        getAllData();
    }
}
