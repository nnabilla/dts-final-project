package com.nabilla.finalproject;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nabilla.finalproject.database.DBHelper;

public class BeliRotiActivity extends AppCompatActivity {
    EditText txt_id, txt_name, txt_price, txt_imgurl, txt_pemesan, txt_latitude, txt_longitude;
    Button btn_submit, btn_cancel;
    DBHelper SQLite = new DBHelper(this);
    String id, name, price, imgurl, latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beli_roti);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txt_id = findViewById(R.id.txt_id);
        txt_name = findViewById(R.id.txt_nama);
        txt_price = findViewById(R.id.txt_price);
        txt_imgurl = findViewById(R.id.txt_url);
        txt_pemesan = findViewById(R.id.txt_pemesan);
        txt_latitude = findViewById(R.id.txt_latitude);
        txt_longitude = findViewById(R.id.txt_longitude);

        btn_submit = findViewById(R.id.btn_submit);
        btn_cancel = findViewById(R.id.btn_cancel);

        id = getIntent().getStringExtra(DetailActivity.TAG_ID);
        name = getIntent().getStringExtra(DetailActivity.TAG_NAME);
        price = getIntent().getStringExtra(DetailActivity.TAG_PRICE);
        imgurl = getIntent().getStringExtra(DetailActivity.TAG_IMGURL);
        latitude = getIntent().getStringExtra(DetailActivity.TAG_LATITUDE);
        longitude = getIntent().getStringExtra(DetailActivity.TAG_LONGITUDE);
        setTitle("Save Data");
        txt_name.setText(name);
        txt_name.setEnabled(false);
        txt_price.setText(price);
        txt_price.setEnabled(false);
        txt_imgurl.setText(imgurl);
        txt_imgurl.setEnabled(false);
        txt_latitude.setText(latitude);
        txt_latitude.setEnabled(false);
        txt_longitude.setText(longitude);
        txt_longitude.setEnabled(false);

        btn_submit.setOnClickListener(v -> {
            try {
                if (txt_id.getText().toString().equals("")) {
                    save();
                } else {
                    edit();
                }
            } catch (Exception e) {
                Log.e("Submit", e.toString());
            }
        });

        btn_cancel.setOnClickListener(v -> {
            blank();
            finish();
        });

    }

    private void blank() {
        txt_name.requestFocus();
        txt_id.setText(null);
        txt_name.setText(null);
        txt_price.setText(null);
        txt_imgurl.setText(null);
        txt_pemesan.setText(null);
        txt_latitude.setText(null);
        txt_longitude.setText(null);

    }

    private void save() {
        if (String.valueOf(txt_name.getText()).equals(null) || String.valueOf(txt_name.getText()).equals("") ||
                String.valueOf(txt_price.getText()).equals(null) || String.valueOf(txt_price.getText()).equals("") ||
                String.valueOf(txt_imgurl.getText()).equals(null) || String.valueOf(txt_imgurl.getText()).equals("") ||
                String.valueOf(txt_pemesan.getText()).equals(null) || String.valueOf(txt_imgurl.getText()).equals("") ||
                String.valueOf(txt_latitude.getText()).equals(null) || String.valueOf(txt_latitude.getText()).equals("") ||
                String.valueOf(txt_longitude.getText()).equals(null) || String.valueOf(txt_longitude.getText()).equals("")) {
            Toast.makeText(getApplicationContext(), "Please input name or address ...", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.insertTransaksi(
                    txt_name.getText().toString().trim(),
                    txt_price.getText().toString().trim(),
                    txt_imgurl.getText().toString().trim(),
                    txt_pemesan.getText().toString().trim(),
                    txt_latitude.getText().toString().trim(),
                    txt_longitude.getText().toString().trim());
            blank();
            finish();
        }
    }

    private void edit() {
        if (String.valueOf(txt_name.getText()).equals(null) || String.valueOf(txt_name.getText()).equals("") ||
                String.valueOf(txt_price.getText()).equals(null) || String.valueOf(txt_price.getText()).equals("") ||
                String.valueOf(txt_imgurl.getText()).equals(null) || String.valueOf(txt_imgurl.getText()).equals("") ||
                String.valueOf(txt_pemesan.getText()).equals(null) || String.valueOf(txt_imgurl.getText()).equals("") ||
                String.valueOf(txt_latitude.getText()).equals(null) || String.valueOf(txt_latitude.getText()).equals("") ||
                String.valueOf(txt_longitude.getText()).equals(null) || String.valueOf(txt_longitude.getText()).equals("")) {
            Toast.makeText(getApplicationContext(), "Please input name or address ...", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.updateTransaksi(
                    Integer.parseInt(txt_id.getText().toString().trim()),
                    txt_name.getText().toString().trim(),
                    txt_price.getText().toString().trim(),
                    txt_imgurl.getText().toString().trim(),
                    txt_pemesan.getText().toString().trim(),
                    txt_latitude.getText().toString().trim(),
                    txt_longitude.getText().toString().trim());
            blank();
            finish();
        }
    }
}
