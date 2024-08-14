package com.nabilla.finalproject.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import com.nabilla.finalproject.R;
import com.nabilla.finalproject.model.RotiModel;

public class adapterRoti extends BaseAdapter {
    private final Activity activity;
    private LayoutInflater layoutInflater;
    private final List<RotiModel> items;

    public adapterRoti(Activity activity, List<RotiModel> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (layoutInflater == null) {
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_main, null);
        }

        TextView id = view.findViewById(R.id.tv_id);
        TextView name = view.findViewById(R.id.tv_namaRoti);
        TextView harga = view.findViewById(R.id.tv_hargaRoti);
        ImageView img = view.findViewById(R.id.iv_gambar);

        RotiModel rotiModel = items.get(i);

        id.setText(rotiModel.getId());
        name.setText(rotiModel.getNama());
        harga.setText(rotiModel.getHarga());
        Glide.with(view.getContext())
                .load(rotiModel.getImgUrl()).into(img);

        return view;
    }
}
