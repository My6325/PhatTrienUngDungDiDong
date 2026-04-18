package com.example.phim;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    GridView gvMusic;
    MusicAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gvMusic = findViewById(R.id.gvMovie);
        List<Music> movieList = getListData();

        // Gắn Adapter vào GridView
        adapter = new MusicAdapter(this, movieList);
        gvMusic.setAdapter(adapter);

        // 3. Bắt sự kiện click vào một phim trên lưới
        gvMusic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Music selectedMovie = (Music) gvMusic.getItemAtPosition(position);
                Toast.makeText(MainActivity.this,
                        "Đang chọn phim: " + selectedMovie.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Dữ liệu mẫu
    private List<Music> getListData() {
        List<Music> albumList = new ArrayList<>();
        albumList.add(new Music("Ái - tlinh", "ai_tlinh", 25000));
        albumList.add(new Music("99% - MCK", "mck_99", 45500));
        albumList.add(new Music("KOSMIK - SpaceSpeakers", "kosmik", 80000));
        albumList.add(new Music("Ai Cũng Phải Bắt Đầu... - HIEUTHUHAI", "hieuthuhai_album", 65000));
        albumList.add(new Music("Bảo Tàng - B Ray", "bray_baotang", 30000));
        albumList.add(new Music("Đồng Âm - Đen Vâu", "den_dongam", 150000));

        return albumList;
    }
}