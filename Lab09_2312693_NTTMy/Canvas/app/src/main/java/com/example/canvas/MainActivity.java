package com.example.canvas;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    MyCanVas canVas;
    Button button;
    int[] colors=new int[]{Color.RED,Color.GREEN,Color.GRAY,Color.YELLOW,Color.CYAN,Color.WHITE};
    Random rd=new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        canVas=findViewById(R.id.myCanvas);
        button=findViewById(R.id.btnDraw);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int colorIndex=rd.nextInt(colors.length);
                MyCanVas.color=colors[colorIndex];
                canVas.invalidate();
            }
        });
    }
}