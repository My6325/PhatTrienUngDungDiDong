package com.example.firstprogram;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText txtX,txtY;
    TextView txtResult;
    Button btnPlus,btnMinus,btnMulti,btnDivide,btnPercent;
    private void innitControl(){
        txtX=findViewById(R.id.txtX);
        txtY=findViewById(R.id.txtY);
        txtResult=findViewById(R.id.txtResult);
        btnPlus=findViewById(R.id.btnPlus);
        btnMinus=findViewById(R.id.btnMinus);
        btnMulti=findViewById(R.id.btnMulti);
        btnDivide=findViewById(R.id.btnDivide);
        btnPercent=findViewById(R.id.btnPercent);

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double x=Double.parseDouble(txtX.getText().toString());;
                double y=Double.parseDouble((txtY.getText().toString()));
                double result=x+y;
                txtResult.setText(String.valueOf(result));
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double x=Double.parseDouble(txtX.getText().toString());;
                double y=Double.parseDouble((txtY.getText().toString()));
                double result=x-y;
                txtResult.setText(String.valueOf(result));
            }
        });
        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double x=Double.parseDouble(txtX.getText().toString());;
                double y=Double.parseDouble((txtY.getText().toString()));
                double result=x*y;
                txtResult.setText(String.valueOf(result));
            }
        });
        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double x=Double.parseDouble(txtX.getText().toString());;
                double y=Double.parseDouble((txtY.getText().toString()));
                if (y == 0) {
                    txtResult.setText("Không thể chia cho 0");
                    return;
                }
                double result=x/y;
                txtResult.setText(String.valueOf(result));
            }
        });
        btnPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x=Integer.parseInt(txtX.getText().toString());;
                int y=Integer.parseInt((txtY.getText().toString()));
                int result=x%y;
                txtResult.setText(String.valueOf(result));
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        innitControl();
    }
}