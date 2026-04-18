package com.example.luyentap;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyListViewAdapter extends BaseAdapter {
    // Danh sách dữ liệu hiển thị
    ArrayList<Product> list;
    public MyListViewAdapter(ArrayList<Product> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        // Trả về số lượng phần tử trong danh sách
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // Trả về đối tượng dữ liệu tại vị trí position
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // Trả về ID của dòng, thường để mặc định là 0 nếu không dùng đến
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Tạo (inflate) giao diện từ file row
        View view = View.inflate(parent.getContext(), R.layout.row, null);
        //Lấy dữ liệu ở vị trí hiện tại
        Product product = (Product) getItem(position);

        ImageView img = view.findViewById(R.id.imgRow);
        img.setImageResource(product.image); // Thiết lập hình ảnh

        TextView txtTitle = view.findViewById(R.id.txtTitle);
        txtTitle.setText(product.title); // Thiết lập tiêu đề

        TextView txtContent = view.findViewById(R.id.txtContent);
        txtContent.setText(product.content); // Thiết lập nội dung mô tả

        // Trả về view đã được đổ đầy dữ liệu để hiển thị lên màn hình
        return view;
    }
}
