package edu.huflit.truyentranh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.huflit.truyentranh.databasedoctruyen.databasedoctruyen;

public class MainDangNhap extends AppCompatActivity {

    EditText edtTaiKhoan, edtMatKhau;
    Button btnDangNhap, btnDangKy;


    //Để tạo đối tượng cho database
    databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_nhap);

        AnhXa();


        //Đối tượng databasedoctruyen
        databasedoctruyen = new databasedoctruyen(this);


        //Tạo sự kiện click vào chuyển sang activity đăng ký
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainDangNhap.this, MainDangKy.class);
                startActivity(i);
            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Gán cho các biến là giá trị nhập vào từ editText
                String tentaikhoan = edtTaiKhoan.getText().toString();
                String matkhau = edtMatKhau.getText().toString();


                //Sử dụng con trỏ để lấy dữ liệu, gọi tới getData() để lấy tất cẩ tài khoản ở database
                Cursor cursor = databasedoctruyen.getData();

                //Thực hiện vòng lặp để lấy dữ liệu từ cusor với moviToNext() di chuyển tiếp
                while (cursor.moveToNext()){

                    //Lấy dữ liệu và gán vào biến, dữ liệu tài khoản và mật khẩu lần lượt ở ô 1 và 2
                    String datataikhoan = cursor.getString(1);
                    String datamatkhau = cursor.getString(2);

                    //Dữ liệu ô 0 là idtaikhoan, ô 3 là email, ô 4 là phân quyền

                    //Nếu tk và mk nhập khớp với ở database
                    if(datataikhoan.equals(tentaikhoan) && datamatkhau.equals(matkhau)){
                        //Lấy dữ liệu phanquyen va id
                        int phanquyen = cursor.getInt(4);
                        int idd = cursor.getInt(0);

                        String email = cursor.getString(3);
                        String tentk = cursor.getString(1);


                        //Chuyển qua màn hình MainActivity
                        Intent i = new Intent(MainDangNhap.this, MainActivity.class);


                        //Gửi dữ liệu qua Activity là MainActivity
                        i.putExtra("phanq", phanquyen);
                        i.putExtra("idd", idd);
                        i.putExtra("email", email);
                        i.putExtra("tentaikhoan", tentaikhoan);

                        startActivity(i);
                        Log.e("Đăng nhập : ","Thành công");
                    }
                    else{
                        Log.e("Đăng nhập : ","Không thành công");
                    }
                }

                //Thực hiện trả cusor về đầu
                cursor.moveToFirst();
                //đóng khi không dùng
                cursor.close();
            }
        });
    }


    private void AnhXa(){
        edtTaiKhoan = findViewById(R.id.edtTaiKhoan);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        btnDangKy = findViewById(R.id.btnDangKy);
        btnDangNhap = findViewById(R.id.btnDangNhap);
    }
}