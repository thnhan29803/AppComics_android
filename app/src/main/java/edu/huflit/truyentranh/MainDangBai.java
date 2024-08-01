package edu.huflit.truyentranh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import edu.huflit.truyentranh.databasedoctruyen.databasedoctruyen;
import edu.huflit.truyentranh.model.Truyen;

public class MainDangBai extends AppCompatActivity {

    EditText edtTenTruyen, edtNoiDung, edtAnh;
    CheckBox chkChapter;
    Button btnDangBai;
    databasedoctruyen databasedoctruyen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_bai);

        chkChapter = findViewById(R.id.chkChapter);
        edtAnh = findViewById(R.id.dbimg);
        edtTenTruyen = findViewById(R.id.dbTenTruyen);
        edtNoiDung = findViewById(R.id.dbnoidung);
        btnDangBai = findViewById(R.id.dbdangbai);

        databasedoctruyen = new databasedoctruyen(this);

        // nếu như tích chọn truyện có chapter thì xóa phần nội dung
        chkChapter.setOnClickListener(view -> {
            if (chkChapter.isChecked()) {
                edtNoiDung.setText("");
                edtNoiDung.setEnabled(false);
            } // ngược lại
            else {
                edtNoiDung.setEnabled(true);
            }
        });

        //button đăng bài
        btnDangBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //lấy nội dung được nhập vào EditText
                String tentruyen = edtTenTruyen.getText().toString();
                String noidung = edtNoiDung.getText().toString();
                String img = edtAnh.getText().toString();

                //Khởi tạo biến truyen với kiểu Truyen và gọi hàm CreateTruyen() để tạo truyện
                Truyen truyen = CreateTruyen();

                // Kiểm tra xem có đủ điều kiện chưa
                if(tentruyen.equals("") || (noidung.equals("")&& !chkChapter.isChecked()) || img.equals("")){
                    Toast.makeText(MainDangBai.this, "Yêu cầu nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    Log.e("ERR : ", "Nhập đủ thông tin");

                }

                //Nếu nhập đủ thông tin thì thực hiện thêm dữ liệu
                else {
                    databasedoctruyen.AddTruyen(truyen);

                    //Chuyển qua màn Admin và cập nhật lại dữ liệu
                    Intent intent = new Intent(MainDangBai.this, MainAdmin.class);
                    finish();
                    startActivity(intent);
                }
            }
        });


    }


    //Phương thức tạo truyện
    private Truyen CreateTruyen(){

        //Lấy nội dung để tạo truyện
        String tentruyen = edtTenTruyen.getText().toString();
        String noidung = edtNoiDung.getText().toString();
        String img = edtAnh.getText().toString();

        Intent intent = getIntent();


        int id = intent.getIntExtra("Id",0);
        Truyen truyen = new Truyen(tentruyen, noidung, img, id);
        return truyen;
    }
}