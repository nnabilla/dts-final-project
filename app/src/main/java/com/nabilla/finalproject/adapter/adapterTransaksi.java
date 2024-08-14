package com.nabilla.finalproject.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import com.nabilla.finalproject.R;
import com.nabilla.finalproject.model.TransaksiModel;

public class adapterTransaksi extends BaseAdapter {
    private final Activity activity;
    private LayoutInflater layoutInflater;
    private final List<TransaksiModel> items;

    public adapterTransaksi(Activity activity, List<TransaksiModel> items) {
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
            view = layoutInflater.inflate(R.layout.list_transaksi, null);
        }

        TextView id = view.findViewById(R.id.tv_id);
        TextView pemesan = view.findViewById(R.id.tv_namaPemesan);
        TextView name = view.findViewById(R.id.tv_namaRoti);
        TextView harga = view.findViewById(R.id.tv_hargaRoti);
        TextView lat = view.findViewById(R.id.tv_latitude);
        TextView Long = view.findViewById(R.id.tv_longitude);

        TransaksiModel transaksiModel = items.get(i);

        id.setText(transaksiModel.getId());
        pemesan.setText(transaksiModel.getPemesan());
        name.setText(transaksiModel.getNama());
        harga.setText(transaksiModel.getHarga());
        lat.setText(transaksiModel.getLatitude());
        Long.setText(transaksiModel.getLongitude());


        return view;
    }
}
