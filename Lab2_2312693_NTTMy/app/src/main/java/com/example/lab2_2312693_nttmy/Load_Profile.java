package com.example.lab2_2312693_nttmy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Load_Profile extends AppCompatActivity {
    ImageButton ic_back;
    ImageView ic_phone;
    TextView txtPhone1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m001_act_profile);

        ic_back=findViewById(R.id.ic_backProfile);
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txtPhone1=findViewById(R.id.txtPhone1);
        ic_phone=findViewById(R.id.ic_phone);
        ic_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = txtPhone1.getText().toString();

                // Sử dụng Intent.ACTION_DIAL để chuyển sang màn hình cuộc gọi
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber.trim()));
                startActivity(intent);
            }
        });
    }

}
