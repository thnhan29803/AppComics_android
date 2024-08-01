package edu.huflit.truyentranh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import edu.huflit.truyentranh.adapter.adapterTruyen;
import edu.huflit.truyentranh.databasedoctruyen.databasedoctruyen;
import edu.huflit.truyentranh.model.Truyen;

public class MainTimKiem extends AppCompatActivity {

    ListView listView;

    EditText edt;

    ArrayList<Truyen> TruyenArrayList;

    ArrayList<Truyen> arrayList;

    adapterTruyen adapterTruyen;

    databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tim_kiem);

        listView = findViewById(R.id.listviewtimkiem);
        edt = findViewById(R.id.timkiem);

        innitList();

        //Bât click cho item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String noidung = arrayList.get(i).getNoiDung();

                if (noidung.isEmpty() || noidung == null) {
                    //Tạo sự kiện click vào item trên list view sẽ đi đến trang nội dung truyện
                    Intent intent = new Intent(MainTimKiem.this, MainChapter.class);
                    int id = arrayList.get(i).getID();
                    //Gửi dữ liệu
                    intent.putExtra("idtruyen", id);
                    startActivity(intent);
                } else {
                    //Tạo sự kiện click vào item trên list view sẽ đi đến trang nội dung truyện
                    Intent intent = new Intent(MainTimKiem.this, MainNoiDung.class);
                    String tent = arrayList.get(i).getTenTruyen();
                    String noidungt = arrayList.get(i).getNoiDung();
                    //Gửi dữ liệu
                    intent.putExtra("tentruyen", tent);
                    intent.putExtra("noidung", noidungt);
                    startActivity(intent);
                }
            }
        });

        //EditText search
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }
    //Tim kiếm
    private void filter(String text){

        //Xóa dữ liệu mảng
        arrayList.clear();

        ArrayList<Truyen> filteredList = new ArrayList<>();

        for(Truyen item : TruyenArrayList){
            if(item.getTenTruyen().toLowerCase().contains(text.toLowerCase())){

                //Thêm item vào filterList
                filteredList.add(item);

                arrayList.add(item);
            }
        }

        adapterTruyen.fiterList(filteredList);
    }

    //Phương thức lấy dữ liệu, gắn vào listview
    private void innitList() {
        //Tạo Mảng list cho truyện
        TruyenArrayList = new ArrayList<>();

        arrayList = new ArrayList<>();

        databasedoctruyen = new databasedoctruyen(this);

        Cursor cursor = databasedoctruyen.getData2(); // Con trỏ đến và lấy dữ liệu tất cả truyện

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String tentruyen = cursor.getString(1);
            String noidung = cursor.getString(2);
            String anh = cursor.getString(3);
            int id_tk = cursor.getInt(4);

            TruyenArrayList.add(new Truyen(id, tentruyen, noidung, anh, id_tk));

            arrayList.add(new Truyen(id, tentruyen, noidung, anh, id_tk));

            adapterTruyen = new adapterTruyen(getApplicationContext(), TruyenArrayList);

            listView.setAdapter(adapterTruyen);
        }
        cursor.moveToFirst();
        cursor.close();
    }
}