package com.example.quanlymonan;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class MonAnAdapter extends BaseAdapter {
    private final Context context;
    private final List<MonAn> monAnList;

    public MonAnAdapter(Context context, List<MonAn> monAnList) {
        this.context = context;
        this.monAnList = monAnList;
    }

    @Override public int getCount()              { return monAnList.size(); }
    @Override public Object getItem(int pos)     { return monAnList.get(pos); }
    @Override public long getItemId(int pos)     { return monAnList.get(pos).getId(); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_monan, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        MonAn monAn = monAnList.get(position);
        if (monAn.getHinhUri() != null) {
            holder.imgMonAn.setImageURI(Uri.parse(monAn.getHinhUri()));
        } else {
            holder.imgMonAn.setImageResource(monAn.getHinhResId());
        }

        holder.txtTen.setText(monAn.getTen());

        String moTa = monAn.getMoTa();
        holder.txtMoTa.setText((moTa == null || moTa.trim().isEmpty()) ? "Không có mô tả" : moTa);

        holder.txtGia.setText(String.format(Locale.getDefault(), "%,d VND", monAn.getGia()));
        return convertView;
    }

    private static class ViewHolder {
        final ImageView imgMonAn;
        final TextView txtTen, txtMoTa, txtGia;

        ViewHolder(View v) {
            imgMonAn = v.findViewById(R.id.imgMonAn);
            txtTen   = v.findViewById(R.id.txtTen);
            txtMoTa  = v.findViewById(R.id.txtMoTa);
            txtGia   = v.findViewById(R.id.txtGia);
        }
    }
}
