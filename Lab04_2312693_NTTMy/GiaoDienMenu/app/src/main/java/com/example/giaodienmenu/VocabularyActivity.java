package com.example.giaodienmenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VocabularyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vocabulary);

        TextView tvTopicTitle = findViewById(R.id.tv_topic_title);
        ListView lvWords = findViewById(R.id.lv_words);

        // Nhận dữ liệu (Intent) từ MainActivity gửi sang
        Intent intent = getIntent();
        String topicName = intent.getStringExtra("TOPIC_NAME");
        String[] wordsArray = intent.getStringArrayExtra("WORD_LIST");

        // Hiển thị tên chủ đề lên TextView
        if (topicName != null) {
            tvTopicTitle.setText(topicName);
        }

        //Đổ dữ liệu mảng từ vựng vào ListView thông qua ArrayAdapter
        if (wordsArray != null) {
            // Sử dụng layout mặc định của Android (simple_list_item_1) cho ListView
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    wordsArray
            );
            lvWords.setAdapter(adapter);
        }

        //Sự kiện nút back
        ImageView ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lệnh finish() sẽ đóng màn hình VocabularyActivity hiện tại
                // và tự động quay trở về màn hình MainActivity trước đó
                finish();
            }
        });
    }
}
