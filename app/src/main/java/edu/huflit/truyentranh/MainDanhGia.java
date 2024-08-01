package edu.huflit.truyentranh;

import static edu.huflit.truyentranh.MainActivity.tentaikhoan;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.huflit.truyentranh.databasedoctruyen.databasedoctruyen;
import edu.huflit.truyentranh.model.DanhGia;

public class MainDanhGia extends AppCompatActivity {


    //Khai báo biến
    EditText edtNoiDungDanhGia,edttentk;
    Button btnDanhGia, btnXemDanhGia;

    //Tạo đối tượng đọc truyện
    databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_danh_gia);


        //Ánh Xạ
        edttentk = findViewById(R.id.edttentk);
        edtNoiDungDanhGia = findViewById(R.id.dbnoidungdanhgia);
        btnDanhGia = findViewById(R.id.dbdanhgia);
        btnXemDanhGia = findViewById(R.id.dbxemdanhgia);

        //Đối tượng database
        databasedoctruyen = new databasedoctruyen(this);


        //Tạo sự kiện click button Đánh giá
        btnDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String noidungdanhgia = edtNoiDungDanhGia.getText().toString();
                edttentk.setText(tentaikhoan);
                DanhGia danhGia = CreatDanhGia();
                danhGia.setTenTruyen(getIntent().getStringExtra("tentruyen"));
                if(noidungdanhgia.equals("") ){
                    Toast.makeText(MainDanhGia.this,"Yêu cầu nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else{
                    databasedoctruyen.AddDanhGia(danhGia);

                    //  String noidungdanhgia = edtNoiDungDanhGia.getText().toString();
                    Intent intent_danh_gia = new Intent(MainDanhGia.this, MainDanhGiaXem.class);
                    finish();
                    startActivity(intent_danh_gia);
                    Toast.makeText(MainDanhGia.this,"Thêm đánh giá thành công",Toast.LENGTH_SHORT).show();
                    Log.e("Thêm đánh giá : ","Thành công");
                }
            }
        });

        btnXemDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainDanhGia.this, MainDanhGiaXem.class);
                startActivity(intent);
                intent.putExtra("dulieu", 0);
                startActivity(intent);
            }
        });


    }
    private DanhGia CreatDanhGia(){
        String noiDungDanhGia = edtNoiDungDanhGia.getText().toString();
        edttentk.setText(tentaikhoan);

                Intent intent = getIntent();
        int id = intent.getIntExtra("Id",0);
        DanhGia danhGia = new DanhGia(tentaikhoan,noiDungDanhGia);
        return danhGia;

    }
}