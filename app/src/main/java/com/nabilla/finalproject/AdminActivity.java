package com.nabilla.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void ListRoti(View view) {
        Intent intent = new Intent(AdminActivity.this, ListRotiActivity.class);
        startActivity(intent);
    }

    public void ListTransaksi(View view) {
        Intent intent = new Intent(AdminActivity.this, TransaksiLogActivity.class);
        startActivity(intent);
    }
}
