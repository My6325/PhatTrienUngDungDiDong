package com.example.phim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MusicAdapter extends BaseAdapter {
    private Context context;
    private List<Music> listData;
    private LayoutInflater layoutInflater;

    public MusicAdapter(Context context, List<Music> listData) {
        this.context = context;
        this.listData = listData;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        ImageView imgMusic;
        TextView txtMusicName;
        TextView txtLuotBan;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            // Nạp layout item_movie.xml
            convertView = layoutInflater.inflate(R.layout.item_music, null);
            holder = new ViewHolder();
            holder.imgMusic = convertView.findViewById(R.id.imgMusic);
            holder.txtMusicName = convertView.findViewById(R.id.txtMusicName);
            holder.txtLuotBan = convertView.findViewById(R.id.txtLuotBan);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Lấy dữ liệu phim hiện tại
        Music movie = this.listData.get(position);

        // Đổ dữ liệu vào View
        holder.txtMusicName.setText(movie.getName());
        holder.txtLuotBan.setText(String.valueOf(movie.getLuotBan()) + " bản");
        // Lấy ID ảnh từ thư mục drawable thông qua tên file (chuỗi String)
        int imageId = getDrawableResIdByName(movie.getImageName());
        if (imageId != 0) {
            holder.imgMusic.setImageResource(imageId);
        }

        return convertView;
    }

    // Hàm tự động tìm ID ảnh trong thư mục drawable
    private int getDrawableResIdByName(String resName) {
        String pkgName = context.getPackageName();
        return context.getResources().getIdentifier(resName, "drawable", pkgName);
    }
}