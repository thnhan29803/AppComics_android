package edu.huflit.truyentranh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import edu.huflit.truyentranh.adapter.adapterDanhGia;
import edu.huflit.truyentranh.databasedoctruyen.databasedoctruyen;
import edu.huflit.truyentranh.model.DanhGia;

public class MainDanhGiaXem extends AppCompatActivity {
    ArrayList<DanhGia> listdanhgia;
    ListView list_view;
    databasedoctruyen databasedoctruyen;
    adapterDanhGia adapterDanhGia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_danh_gia_xem);



        innitlist();
    }

    //Phương thức lấy dữ liệu, gắn vào listview
    private void innitlist() {
        list_view =findViewById(R.id.list_view);
        listdanhgia = new ArrayList<>();

        databasedoctruyen  = new databasedoctruyen(this);

        Cursor cursor3 = databasedoctruyen.getData3();

        //Thực hiện vòng lặp để lấy dữ liệu từ cusor với moviToNext() di chuyển tiếp
        while (cursor3.moveToNext()){

            //Lấy dữ liệu và gán vào biến, dữ liệu noidungdanhgia và tentk lần lượt ở ô 1 và 3, id tài khoản là ô 0, tên truyện ô 2
            int id=cursor3.getInt(0);
            String NoiDungDanhGia =cursor3.getString(1);
            String TenTK=cursor3.getString(3);

            //Add nội dung vào list truyện
            listdanhgia.add(new DanhGia(id,NoiDungDanhGia,TenTK, cursor3.getString(2)));

            //Lưu vào Adapter
            adapterDanhGia = new adapterDanhGia(getApplicationContext(), listdanhgia);
            //Set adapter cho listview
            list_view.setAdapter(adapterDanhGia);
        }
        cursor3.moveToFirst();
        cursor3.close();
    }


}
