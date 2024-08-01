package edu.huflit.truyentranh;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.huflit.truyentranh.adapter.adapterTruyen;
import edu.huflit.truyentranh.databasedoctruyen.databasedoctruyen;
import edu.huflit.truyentranh.model.Truyen;

public class MainAdmin extends AppCompatActivity {

    ListView listView;
    Button buttonThem;

    ArrayList<Truyen> TruyenArrayList;
    adapterTruyen adapterTruyen;

    databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        listView = findViewById(R.id.listviewAdmin);
        buttonThem = findViewById(R.id.buttonThemtruyen);

        initlist();

        //Tạo sư kiện khi click vào button Thêm
        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Lấy id tài khoản để biết tài khoản admin nào đã vào chỉnh sửa
                Intent intent1 = getIntent();
                int id = intent1.getIntExtra("Id", 0);

                //Tiếp tục gửi Id qua màn hình thêm truyện
                Intent intent = new Intent (MainAdmin.this, MainDangBai.class);
                intent.putExtra("Id", id);
                startActivity(intent);
            }
        });


        //Click item long sẽ xóa item
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                DialogDelete(i);

                return false;
            }
        });

        /*//Click item để chỉnh sửa
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainAdmin.this, MainChinhSua.class);
                String tent = TruyenArrayList.get(i).getTenTruyen();
                String noidung = TruyenArrayList.get(i).getNoiDung();
                String anh = TruyenArrayList.get(i).getAnh();



                intent.putExtra("tentruyen", tent);
                intent.putExtra("noidung", noidung);
                intent.putExtra("anh", anh);
                startActivity(intent);
            }
        });*/
    }

    //Phương thức Dialog hiện thị cửa sổ xóa
    private void DialogDelete(int i){
        //Tạo đối tượng dialog
        Dialog dialog = new Dialog(this);
        //Nạp layout vào dialog
        dialog.setContentView(R.layout.dialogdelete);
        //Tắt click ra ngoài là đóng, chỉ click no mới đóng
        dialog.setCanceledOnTouchOutside(false);


        //Ánh xạ
        Button btnYes = dialog.findViewById(R.id.buttonYes);
        Button btnNo = dialog.findViewById(R.id.buttonNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idtruyen = TruyenArrayList.get(i).getID();

                //Xóa dữ liệu - gọi hàm bên database
                databasedoctruyen.Delete(idtruyen);

                //Cập nhật lại Activity
                Intent intent = new Intent(MainAdmin.this, MainAdmin.class);
                finish();
                startActivity(intent);
                Toast.makeText(MainAdmin.this, "Xóa truyện thành công", Toast.LENGTH_SHORT).show();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //thực hiện đóng dialog
                dialog.cancel();
            }
        });

        //Run dialog
        dialog.show();
    }


    


    //Gán dữ liệu cho listview
    private void initlist() {
        TruyenArrayList = new ArrayList<>();

        databasedoctruyen = new databasedoctruyen(this);


        //Lấy tất cả truyện
        Cursor cursor1 = databasedoctruyen.getData2();

        //Thực hiện vòng lặp
        while (cursor1.moveToNext()){
            int id = cursor1.getInt(0);
            String tentruyen = cursor1.getString(1);
            String noidung = cursor1.getString(2);
            String anh = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);

            TruyenArrayList.add(new Truyen(id, tentruyen, noidung, anh, id_tk));

            adapterTruyen = new adapterTruyen(getApplicationContext(), TruyenArrayList);

            listView.setAdapter(adapterTruyen);
        }
        cursor1.moveToFirst();
        cursor1.close();
    }
}