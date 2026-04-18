package com.example.luyentap;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //String[] arr=new String[]{"Android","iOS","Window Phone"};
    String[] titles = new String[]{"Android", "iOS", "Window Phone"};
    String[] contents = new String[]{
            "Đây là hệ điều hành Android",
            "Đây là hệ điều hành iOS",
            "Đây là hệ điều hành Window Phone"
    };
    // Đảm bảo bạn đã copy file ảnh vào thư mục res/drawable
    int[] imgs = new int[]{
            R.drawable.android,
            R.drawable.ios,
            R.drawable.window_phone
    };
    ListView lvMain;
    TextView txtDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        lvMain=findViewById(R.id.lvMain);
        txtDisplay=findViewById(R.id.txtDisplay);
        //khởi tạo mảng adapter và truyền mảng arr vào
        //ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arr);

        //Khởi tạo danh sách đối tượng Product
        ArrayList<Product> list = new ArrayList<>();

        // Sử dụng vòng lặp để gộp dữ liệu từ 3 mảng trên vào list đối tượng
        for (int i = 0; i < titles.length; i++) {
            list.add(new Product(imgs[i], titles[i], contents[i]));
        }

        // Khởi tạo Custom Adapter với danh sách Product vừa tạo
        MyListViewAdapter adapter = new MyListViewAdapter(list);

        //gán adapter cho listview
        lvMain.setAdapter(adapter);

        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                txtDisplay.setText("Bạn chọn: "+titles[position]);
            }
        });
    }
}