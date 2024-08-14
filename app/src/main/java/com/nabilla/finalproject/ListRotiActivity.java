package com.nabilla.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.nabilla.finalproject.adapter.adapterRoti;
import com.nabilla.finalproject.database.DBHelper;
import com.nabilla.finalproject.model.RotiModel;

public class ListRotiActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_list_roti);

        //Tambah SQLite
        SQLite = new DBHelper(getApplicationContext());

        //Tambah Floating Action Button
        FloatingActionButton fab = findViewById(R.id.fab);
        //Tambah List View
        listView = findViewById(R.id.list_view);

        fab.setOnClickListener(view -> {

            //Tambah intent untuk pindah ke halaman Add dan Edit
            Intent intent = new Intent(ListRotiActivity.this, AddEditRotiActivity.class);
            startActivity(intent);
        });

        adapter = new adapterRoti(ListRotiActivity.this, itemList);
        Log.e("TAG", "onCreate: " + adapter.toString());
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            final String idx = itemList.get(position).getId();
            final String name = itemList.get(position).getNama();
            final String price = itemList.get(position).getHarga();
            final String imgurl = itemList.get(position).getImgUrl();

            final CharSequence[] dialogitem = {"Edit", "Delete"};
            dialog = new AlertDialog.Builder(ListRotiActivity.this);
            dialog.setCancelable(true);
            dialog.setItems(dialogitem, (dialog, which) -> {
                switch (which) {
                    case 0:
                        Intent intent = new Intent(ListRotiActivity.this, AddEditRotiActivity.class);
                        intent.putExtra(TAG_ID, idx);
                        intent.putExtra(TAG_NAME, name);
                        intent.putExtra(TAG_PRICE, price);
                        intent.putExtra(TAG_IMGURL, imgurl);
                        startActivity(intent);
                        break;
                    case 1:
                        SQLite.deleteRoti(Integer.parseInt(idx));
                        itemList.clear();
                        getAllData();
                        break;
                }
            }).show();
            return false;
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
