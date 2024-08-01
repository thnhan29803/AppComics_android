package edu.huflit.truyentranh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import edu.huflit.truyentranh.adapter.adapterTruyen;
import edu.huflit.truyentranh.adapter.adapterchuyenmuc;
import edu.huflit.truyentranh.adapter.adapterthongtin;
import edu.huflit.truyentranh.databasedoctruyen.databasedoctruyen;
import edu.huflit.truyentranh.model.TaiKhoan;
import edu.huflit.truyentranh.model.Truyen;
import edu.huflit.truyentranh.model.chuyenmuc;

public class MainActivity extends AppCompatActivity {

    public static int idquyen;
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    ListView listView, listViewNew, listViewThongTin;
    DrawerLayout drawerLayout;

    public static String email;
    public static String tentaikhoan;


    // Khai báo các biến truyện
    ArrayList<Truyen> TruyenArrayList;
    adapterTruyen adapterTruyen;

    //Tạo đối tượng databasedoctruyen
    databasedoctruyen databasedoctruyen;


    //Khai báo biến hiện thị chuyên mục
    ArrayList<chuyenmuc> chuyenmucArrayList;

    ArrayList<TaiKhoan> taiKhoanArrayList;

    adapterchuyenmuc adapterchuyenmuc;

    adapterthongtin adapterthongtin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databasedoctruyen = new databasedoctruyen(this);

        //Nhận dữ liệu ở màn đăng nhập gửi
        Intent intentqp = getIntent();
        idquyen = intentqp.getIntExtra("phanq", 0);
        int idd = intentqp.getIntExtra("idd", 0);
        email = intentqp.getStringExtra("email");
        tentaikhoan = intentqp.getStringExtra("tentaikhoan");

        AnhXa();
        ActionBar();
        ActionViewFlipper();


        //Bật sử kiện click item (MainNoidung, 7 truyện mới nhất)
        listViewNew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String noidung = TruyenArrayList.get(i).getNoiDung();
                if (noidung.isEmpty() || noidung == null) {
                    //Tạo sự kiện click vào item trên list view sẽ đi đến trang nội dung truyện
                    Intent intent = new Intent(MainActivity.this, MainChapter.class);
                    int id = TruyenArrayList.get(i).getID();
                    //Gửi dữ liệu
                    intent.putExtra("idtruyen", id);
                    startActivity(intent);
                } else {
                    //Tạo sự kiện click vào item trên list view sẽ đi đến trang nội dung truyện
                    Intent intent = new Intent(MainActivity.this, MainNoiDung.class);
                    String tent = TruyenArrayList.get(i).getTenTruyen();
                    String noidungt = TruyenArrayList.get(i).getNoiDung();
                    //Gửi dữ liệu
                    intent.putExtra("tentruyen", tent);
                    intent.putExtra("noidung", noidungt);
                    startActivity(intent);
                }
            }
        });



        //Bật click item chuyên mục
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //Đăng bài
                if(position == 0){
                    if(idquyen == 2) { //Quyền của admin
                        Intent intent = new Intent(MainActivity.this, MainAdmin.class);

                        //Gửi id tài khoản qua màn admin
                        intent.putExtra("Id", idd);
                        startActivity(intent);
                    }
                    else{ // i == 1 là quyền của người dùng
                        Toast.makeText(MainActivity.this, "Bạn không có quyền đăng bài", Toast.LENGTH_SHORT).show();
                        Log.e("Đăng bài : ", "Bạn không có quyền");
                    }
                }
                //Nếu vị trí ấn vào là thông tin thì sẽ chuyển qua màn thông tin app
                else if(position == 1){
                    Intent intent = new Intent(MainActivity.this, MainThongTin.class);
                    startActivity(intent);
                }

                //Đăng xuất (user, admin)
                else if(position == 2){
                    finish();
                }
            }
        });

    }

    //Thanh actionbar với toolbar
    private void ActionBar(){
        //Hàm hỗ trợ toolbar
        setSupportActionBar(toolbar);
        //set nút cho actionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Tạo icon cho toolbar
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);


        //Bật sự kiện click
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


    }

    //Phương thức cho chạy quảng cáo với ViewFlipper
    private void ActionViewFlipper() {
        //Mảng chứa tấm ảnh cho quảng cáo
        ArrayList<String> mangquangcao = new ArrayList<>();
        //Add ảnh vào
        mangquangcao.add("https://img5.thuthuatphanmem.vn/uploads/2021/09/29/top-20-manhwa-hay-nhat-hien-nay-khong-the-bo-lo_084931909.jpg");
        mangquangcao.add("https://img5.thuthuatphanmem.vn/uploads/2021/09/29/the-god-of-high-school_085224021.jpg");
        mangquangcao.add("https://img5.thuthuatphanmem.vn/uploads/2021/09/29/noblesse_085240414.jpg");
        mangquangcao.add("https://img5.thuthuatphanmem.vn/uploads/2021/09/29/lookism_085442324.jpg");
        mangquangcao.add("https://img5.thuthuatphanmem.vn/uploads/2021/09/29/tower-of-god_085527343.jpg");

        //Thực hiện vòng lặp for gán ảnh vào ImageView, rồi từ imgView lên app
        for(int i = 0; i < mangquangcao.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());

            //Sử dụng hàm thư viện Piscasso
            Picasso.get().load(mangquangcao.get(i)).into(imageView);


            //Phương thức chỉnh tấm hình vừa khung quản cáo
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            //Thêm ảnh từ imageview vào ViewFlipper
            viewFlipper.addView(imageView);
        }

        //Thiết lập tư động chạy cho viewFlipper chạy trong 4s
        viewFlipper.setFlipInterval(3000);
        // Chạy auto Flipper
        viewFlipper.setAutoStart(true);

        //Gọi animation cho vào và ra
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);

        //Gọi Animation và viewFlipper
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setInAnimation(animation_slide_out);
    }

    //Phương thức ánh xạ
    private void AnhXa(){
        toolbar = findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewFlipper);
        listViewNew = findViewById(R.id.listviewNew);
        listView = findViewById(R.id.listviewmanhinhchinh);
        listViewThongTin = findViewById(R.id.listviewthongtin);
        drawerLayout = findViewById(R.id.drawerlayout);




        //Hiện thị 7 truyện mới nhất lên trang
        TruyenArrayList = new ArrayList<>();

        //Lấy 7 truyện mới nhất
        Cursor cursor1 = databasedoctruyen.getData1();


        //Thực hiện vòng lặp để lấy dữ liệu từ cusor với moviToNext() di chuyển tiếp
        while (cursor1.moveToNext()){

            //List truyện gồm 0.id, 1.tentruyen, 2.noidung, 3.anh, 4.id_tk.
            int id = cursor1.getInt(0);
            String tentruyen = cursor1.getString(1);
            String noidung = cursor1.getString(2);
            String anh = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);

            //Add nội dung vào list truyện
            TruyenArrayList.add(new Truyen(id, tentruyen, noidung, anh, id_tk));

                //Lưu vào Adapter
                adapterTruyen = new adapterTruyen(getApplicationContext(), TruyenArrayList);
                //Set adapter cho listview
                listViewNew.setAdapter(adapterTruyen);
        }

        cursor1.moveToFirst();
        cursor1.close();



        // set các icon cho chuyên mục navigation
        //Thông tin
        taiKhoanArrayList = new ArrayList<>();
        taiKhoanArrayList.add(new TaiKhoan(tentaikhoan, email));

        //Tạo mới adapter và set adapter cho chúng
        adapterthongtin = new adapterthongtin(this, R.layout.navigation_thongtin, taiKhoanArrayList);
        listViewThongTin.setAdapter(adapterthongtin);

        //Chuyên mục
        chuyenmucArrayList = new ArrayList<>();
        chuyenmucArrayList.add(new chuyenmuc("Đăng bài", R.drawable.ic_baseline_post_add_24));
        chuyenmucArrayList.add(new chuyenmuc("Thông tin truyện", R.drawable.ic_baseline_face_24));
        chuyenmucArrayList.add(new chuyenmuc("Đăng xuất", R.drawable.ic_baseline_logout_24));
        //set layout item cho chuyên mục
        adapterchuyenmuc = new adapterchuyenmuc(this, R.layout.chuyenmuc, chuyenmucArrayList);
        listView.setAdapter(adapterchuyenmuc);



    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Khởi tạo menu
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Nếu click vào icon tìm kiếm sẽ chuyển qua màn hình tìm kiếm
        switch (item.getItemId()){
            case R.id.menu1: //click tìm kiếm
                Intent i = new Intent(MainActivity.this, MainTimKiem.class);
                startActivity(i);
                break;
            case R.id.login1: //click đăng nhập
                Intent intent = new Intent(MainActivity.this, MainDangNhap.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}