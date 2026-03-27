package com.example.doidodai;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText txtLength;
    private Spinner spnLengthUnit;
    private TextView[] lblResults;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        // Đổ dữ liệu vào Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spnLengthUnit.setAdapter(adapter);

        // Lắng nghe sự kiện thay đổi đơn vị trên Spinner
        spnLengthUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                changeLengthUnit();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Lắng nghe sự kiện khi gõ số vào EditText
        txtLength.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                changeLengthUnit();
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}
        });

    }
    private String[] units = {
            "Hải lý", "Dặm", "Kilometer", "Lý", "Met", "Yard", "Foot", "Inches"
    };

    private double[][] ratio = {
            {1, 1.5077945, 1.8520000, 20.2537183, 1852.0000, 2025.37183, 6076.11549, 72913.38583},
            {0.06897624, 1, 1.6093440, 17.6000000, 1609.3440, 1760.00000, 5280.00000, 63360.00000},
            {0.53995680, 0.62137119, 1, 10.9361330, 1000.0000, 1093.61330, 3280.83990, 39370.07874},
            {0.04937365, 0.05681818, 0.0914400, 1, 91.4400, 100.00000, 300.00000, 3600.00000},
            {0.00053996, 0.00062137, 0.0010000, 0.0109361, 1.0000, 1.09361, 3.28084, 39.37008},
            {0.00049374, 0.00056818, 0.0009144, 0.0100000, 0.9144, 1.00000, 3, 36},
            {0.00016458, 0.00018939, 0.0003048, 0.0033333, 0.3048, 0.33333, 1, 12},
            {0.00001371, 0.00001578, 0.0000254, 0.0002778, 0.0254, 0.02778, 0.08333, 1}
    };
    // Hàm ánh xạ ID
    private void init() {
        txtLength = findViewById(R.id.txtLength);
        spnLengthUnit = findViewById(R.id.spnLengthUnit);

        // Đảm bảo thứ tự này khớp với thứ tự các đơn vị trong mảng units[]
        lblResults = new TextView[]{
                findViewById(R.id.lblNauticalMile),
                findViewById(R.id.lblMile),
                findViewById(R.id.lblKilometer),
                findViewById(R.id.lblLi),
                findViewById(R.id.lblMeter),
                findViewById(R.id.lblYard),
                findViewById(R.id.lblFoot),
                findViewById(R.id.lblInch)
        };
    }

    // Hàm tính toán cốt lõi
    private void changeLengthUnit() {
        int rowIdx = spnLengthUnit.getSelectedItemPosition();
        if (rowIdx < 0) rowIdx = 0;

        String input = txtLength.getText().toString();
        if (input.isEmpty()) input = "0";

        double number = Double.valueOf(input);

        // Dùng Locale.US để đảm bảo dấu phẩy (,) phân cách hàng nghìn và dấu chấm (.) cho thập phân
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        // Pattern "#,##0.0000" ép hệ thống KHÔNG hiện chữ E và LUÔN giữ 4 số lẻ phía sau
        DecimalFormat df = new DecimalFormat("#,##0.0000", symbols);

        for (int i = 0; i < lblResults.length; i++) {
            double temp = number * ratio[rowIdx][i];

            // Format kết quả và in ra màn hình
            lblResults[i].setText(df.format(temp));
        }
    }
}