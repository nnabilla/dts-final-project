package com.nabilla.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ControlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
    }

    public void Admin(View view) {
        Intent intent = new Intent(ControlActivity.this, AdminActivity.class);
        startActivity(intent);
    }

    public void Pengguna(View view) {
        Intent intent = new Intent(ControlActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
