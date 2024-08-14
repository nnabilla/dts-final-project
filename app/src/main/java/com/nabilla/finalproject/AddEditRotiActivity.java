package com.nabilla.finalproject;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.nabilla.finalproject.R;

import com.nabilla.finalproject.database.DBHelper;

public class AddEditRotiActivity extends AppCompatActivity {
    EditText txt_id, txt_name, txt_price, txt_imgurl;
    Button btn_submit, btn_cancel;
    DBHelper SQLite = new DBHelper(this);
    String id, name, price, imgurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_roti);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txt_id = findViewById(R.id.txt_id);
        txt_name = findViewById(R.id.txt_nama);
        txt_price = findViewById(R.id.txt_price);
        txt_imgurl = findViewById(R.id.txt_url);
        btn_submit = findViewById(R.id.btn_submit);
        btn_cancel = findViewById(R.id.btn_cancel);

        id = getIntent().getStringExtra(MainActivity.TAG_ID);
        name = getIntent().getStringExtra(MainActivity.TAG_NAME);
        price = getIntent().getStringExtra(MainActivity.TAG_PRICE);
        imgurl = getIntent().getStringExtra(MainActivity.TAG_IMGURL);

        if (id == null || id == "") {
            setTitle("Add Data");
        } else {
            setTitle("Edit Data");
            txt_id.setText(id);
            txt_name.setText(name);
            txt_price.setText(price);
            txt_imgurl.setText(imgurl);
        }
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
    }

    private void save() {
        if (String.valueOf(txt_name.getText()).equals(null) || String.valueOf(txt_name.getText()).equals("") ||
                String.valueOf(txt_price.getText()).equals(null) || String.valueOf(txt_price.getText()).equals("") ||
                String.valueOf(txt_imgurl.getText()).equals(null) || String.valueOf(txt_imgurl.getText()).equals("")) {
            Toast.makeText(getApplicationContext(), "Please input name or address ...", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.insertRoti(txt_name.getText().toString().trim(), txt_price.getText().toString().trim(), txt_imgurl.getText().toString().trim());
            blank();
            finish();
        }
    }

    private void edit() {
        if (String.valueOf(txt_name.getText()).equals(null) || String.valueOf(txt_name.getText()).equals("") ||
                String.valueOf(txt_price.getText()).equals(null) || String.valueOf(txt_price.getText()).equals("") ||
                String.valueOf(txt_imgurl.getText()).equals(null) || String.valueOf(txt_imgurl.getText()).equals("")) {
            Toast.makeText(getApplicationContext(), "Please input name or address ...", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.updateRoti(Integer.parseInt(txt_id.getText().toString().trim()), txt_name.getText().toString().trim(),
                    txt_price.getText().toString().trim(), txt_imgurl.getText().toString().trim());
            blank();
            finish();
        }
    }
}