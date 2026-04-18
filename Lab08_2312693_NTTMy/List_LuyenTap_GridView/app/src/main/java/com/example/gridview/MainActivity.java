package com.example.gridview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    GridView gvCountry;
    ArrayList<Country> list;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Country> image_details = getListData();
        final GridView gridView = (GridView) findViewById(R.id.gvCountry);
        gridView.setAdapter(new Adapter(this, image_details));

        // When the user clicks on the GridItem
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = gridView.getItemAtPosition(position);
                Country country = (Country) o;
                Toast.makeText(MainActivity.this, "Selected :"
                        + " " + country, Toast.LENGTH_LONG).show();
            }
        });
    }
    private  List<Country> getListData() {
        List<Country> list = new ArrayList<Country>();
        Country vietnam = new Country("Vietnam", "imgvn", 98000000);
        Country usa = new Country("United States", "img_american", 320000000);
        Country russia = new Country("Brazil", "img_brazil", 142000000);
        Country australia = new Country("England", "img_england", 23766305);
        Country japan = new Country("Japan", "imgjapan", 126788677);

        list.add(vietnam);
        list.add(usa);
        list.add(russia);
        list.add(australia);
        list.add(japan);

        return list;
    }
}