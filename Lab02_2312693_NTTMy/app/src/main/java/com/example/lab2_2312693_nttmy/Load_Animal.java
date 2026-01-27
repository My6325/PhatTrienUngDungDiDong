package com.example.lab2_2312693_nttmy;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Random;

public class Load_Animal extends AppCompatActivity {
    LinearLayout bgAnimal;
    ImageView imgAnimal;
    ImageButton ic_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m001_act_splash);
        bgAnimal = findViewById(R.id.backgroundAnimal);
        imgAnimal = findViewById(R.id.imgAnimal);

        int[] color = {
                R.color.purple_200,
                R.color.purple_500,
                R.color.purple_700,
        };
        int[] animal = {
                R.drawable.ic_penguin,
                R.drawable.ic_turtle,
                R.drawable.ic_koala
        };
        Random random = new Random();
        int randomAnimal = animal[random.nextInt(animal.length)];
        int randomColor = color[random.nextInt(color.length)];
        imgAnimal.setImageResource(randomAnimal);
        int colorRandom = ContextCompat.getColor(this, randomColor);
        bgAnimal.setBackgroundColor(colorRandom);
        //Cho thời gian load màn hình
        final View layoutLoading=findViewById(R.id.layout_Loading);
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                layoutLoading.setVisibility(View.GONE);
            }
        }, 500);
        ic_back=findViewById(R.id.ic_back);
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
