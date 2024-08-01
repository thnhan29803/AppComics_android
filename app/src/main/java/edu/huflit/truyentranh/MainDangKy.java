package edu.huflit.truyentranh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.huflit.truyentranh.databasedoctruyen.databasedoctruyen;
import edu.huflit.truyentranh.model.TaiKhoan;

public class MainDangKy extends AppCompatActivity {

    EditText edtDKTaiKhoan, edtDKMatKhau, edtDKEmail;
    Button btnDKDangKy, btnDKDangNhap;

    databasedoctruyen databasedoctruyen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_ky);

        AnhXa();

        databasedoctruyen = new databasedoctruyen(this);

        btnDKDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taikhoan = edtDKTaiKhoan.getText().toString();
                String matkhau = edtDKMatKhau.getText().toString();
                String email = edtDKEmail.getText().toString();

                //Gọi phương thức tạo tài khoản
                TaiKhoan taikhoan1 = CreateTaiKhoan();

                //Kiểm tra rỗng
                if(taikhoan.equals("") || matkhau.equals("") || email.equals("")){
                    Toast.makeText(MainDangKy.this,"Bạn chưa nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                    Log.e("Thông báo : ","Bạn chưa nhập đầy đủ thông tin");
                }
                //Nếu đầy đủ thông tin
                else{
                    //Nếu đầy đủ thông tin thì add nó vào database
                    databasedoctruyen.AddTaiKhoan(taikhoan1);
                    Toast.makeText(MainDangKy.this,"Đăng ký thành công ",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Sự kiện click back về đăng nhập
        btnDKDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    //Phương thức tạo tài khoản
    private TaiKhoan CreateTaiKhoan(){
        String taikhoan = edtDKTaiKhoan.getText().toString();
        String matkhau = edtDKMatKhau.getText().toString();
        String email = edtDKEmail.getText().toString();
        int phanquyen = 1; //phân quyền người dùng

        //Tạo mới thông tin 1 tài khoản
        TaiKhoan tk = new TaiKhoan(taikhoan,matkhau,email,phanquyen);

        return tk;
    }
    private void AnhXa() {
        edtDKEmail = findViewById(R.id.dkemail);
        edtDKMatKhau = findViewById(R.id.dkedtMatKhau);
        edtDKTaiKhoan = findViewById(R.id.dkedtTaiKhoan);
        btnDKDangKy = findViewById(R.id.btndkDangKy);
        btnDKDangNhap = findViewById(R.id.btndkDangNhap);

    }
}