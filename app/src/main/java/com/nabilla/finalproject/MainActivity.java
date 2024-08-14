package com.nabilla.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.nabilla.finalproject.adapter.adapterRoti;
import com.nabilla.finalproject.database.DBHelper;
import com.nabilla.finalproject.model.RotiModel;

public class MainActivity extends AppCompatActivity {

    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_PRICE = "price";
    public static final String TAG_IMGURL = "imgurl";
    ListView listView;
    AlertDialog.Builder dialog;
    List<RotiModel> itemList = new ArrayList<>();
    adapterRoti adapter;
    DBHelper SQLite = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Tambah SQLite
        SQLite = new DBHelper(getApplicationContext());

        //Tambah List View
        listView = findViewById(R.id.list_view);

        adapter = new adapterRoti(MainActivity.this, itemList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            final String idx = itemList.get(i).getId();
            final String name = itemList.get(i).getNama();
            final String price = itemList.get(i).getHarga();
            final String imgurl = itemList.get(i).getImgUrl();

            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra(TAG_ID, idx);
            intent.putExtra(TAG_NAME, name);
            intent.putExtra(TAG_PRICE, price);
            intent.putExtra(TAG_IMGURL, imgurl);
            startActivity(intent);

        });

        getAllData();
    }


    void getAllData() {
        ArrayList<HashMap<String, String>> row = SQLite.getAllRotiData();

        for (int i = 0; i < row.size(); i++) {
            String id = row.get(i).get(TAG_ID);
            String nama = row.get(i).get(TAG_NAME);
            String price = row.get(i).get(TAG_PRICE);
            String url = row.get(i).get(TAG_IMGURL);

            RotiModel rotiModel = new RotiModel();

            rotiModel.setId(id);
            rotiModel.setNama(nama);
            rotiModel.setHarga(price);
            rotiModel.setImgUrl(url);

            itemList.add(rotiModel);
        }
        adapter.notifyDataSetChanged();

    }

    protected void onResume() {
        super.onResume();
        itemList.clear();
        getAllData();
    }
}