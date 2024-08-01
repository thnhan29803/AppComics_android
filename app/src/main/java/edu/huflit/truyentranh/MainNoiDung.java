package edu.huflit.truyentranh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainNoiDung extends AppCompatActivity {

    public static TextView txtTenTruyen, txtNoiDung;
    Button btnDanhGia, btnChiaSe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_noi_dung);

        txtNoiDung = findViewById(R.id.noidung);
        txtTenTruyen = findViewById(R.id.TenTruyen);
        btnDanhGia = findViewById(R.id.buttonDanhGia);
        btnChiaSe = findViewById(R.id.buttonChiaSe);

        btnDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainNoiDung.this, MainDanhGia.class);
                intent.putExtra("tentruyen", txtTenTruyen.getText().toString());
                startActivity(intent);
            }
        });


        btnChiaSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainNoiDung.this, MainChiaSe.class);
                startActivity(intent);
            }
        });


        //Lấy dữ liệu truyện
        Intent intent = getIntent();
        String tentruyen = intent.getStringExtra("tentruyen");
        String noidung = intent.getStringExtra("noidung");

        txtTenTruyen.setText(tentruyen);
        txtNoiDung.setText(noidung);

        //Cho phép cuộn nội dung truyện
        txtNoiDung.setMovementMethod(new ScrollingMovementMethod());

    }
}