package edu.huflit.truyentranh;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import edu.huflit.truyentranh.adapter.ChapterAdapter;
import edu.huflit.truyentranh.adapter.IActionRecycler;
import edu.huflit.truyentranh.databasedoctruyen.databasedoctruyen;
import edu.huflit.truyentranh.model.Chapter;
import edu.huflit.truyentranh.model.Truyen;

public class MainChapter extends AppCompatActivity implements IActionRecycler {

    public static final String KEY_URL = "URL";
    public static final String KEY_OBJECT = "CHAPTER";

    private RecyclerView recyclerView;
    private ChapterAdapter chapterAdapter;

    private databasedoctruyen databasedoctruyen;

    private FloatingActionButton btnAddChapter;

    private Button mcbtnDanhGia, mcbtnChiaSe;

    private ArrayList<Chapter> mChapters;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chapter);

        id = getIntent().getIntExtra("idtruyen",-1); // chạy đi


        btnAddChapter = findViewById(R.id.btnAddChapter);
        databasedoctruyen = new databasedoctruyen(this);

        mChapters = new ArrayList<>();
        setDataChapters();

        recyclerView = findViewById(R.id.recyclerview);
        chapterAdapter = new ChapterAdapter(this, this);
        chapterAdapter.setData(mChapters);

        recyclerView.setAdapter(chapterAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mcbtnDanhGia = findViewById(R.id.mcbtnDanhGia);
        mcbtnChiaSe = findViewById(R.id.mcbtnChiaSe);

        //set quyền cho chúng nếu không phải admin thì sẽ ẩn đi
        if(MainActivity.idquyen != 2){
            btnAddChapter.setVisibility(recyclerView.INVISIBLE);
        }

        //Tạp sự kiện click vào floatbutton thêm chapter
        btnAddChapter.setOnClickListener(view ->{
                Intent intent = new Intent(this,MainAddTruyenTranh.class);
                intent.putExtra("idtruyen",id);
                startActivity(intent);
        });

        mcbtnDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainChapter.this, MainDanhGia.class);
                intent.putExtra("tentruyen", 0);
                startActivity(intent);
            }
        });

        mcbtnChiaSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainChapter.this, MainChiaSe.class);
                startActivity(intent);
            }
        });
    }


    private void setDataChapters() {
        mChapters = databasedoctruyen.getAllChapter(id);
//        mChapters.add(new Chapter(1,1,"Chapter 1","https://drive.google.com/file/d/1MGSflI3DJNo73Yy2fg0NyXH1ASt3kzjM/view"));
//        mChapters.add(new Chapter(2,1,"Chapter 2","https://drive.google.com/file/d/1MGSflI3DJNo73Yy2fg0NyXH1ASt3kzjM/view"));
//        mChapters.add(new Chapter(3,1,"Chapter 2","https://drive.google.com/file/d/1MGSflI3DJNo73Yy2fg0NyXH1ASt3kzjM/view"));
//        mChapters.add(new Chapter(4,1,"Chapter 3","https://drive.google.com/file/d/1MGSflI3DJNo73Yy2fg0NyXH1ASt3kzjM/view"));
//        mChapters.add(new Chapter(5,1,"Chapter 4","https://drive.google.com/file/d/1MGSflI3DJNo73Yy2fg0NyXH1ASt3kzjM/view"));
   }

    @Override
    public void sendActivity(String url) {
        Intent intent = new Intent(this,MainShowTruyen.class);
        intent.putExtra(KEY_URL,url);
        startActivity(intent);
    }

    @Override
    public void deleteChapter(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông báo")
                .setMessage("Bạn muốn xóa chapter này ?")
                .setPositiveButton("XÓA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                         databasedoctruyen.deleteChapter(id);
                        setDataChapters();
                        chapterAdapter.setData(mChapters);
                    }
                })
                .setNegativeButton("KHÔNG", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).setIcon(R.drawable.baseline_warning_24)
                .show();
    }

    @Override
    public void editChapter(Chapter chapter) {
        Intent intent = new Intent(this,MainAddTruyenTranh.class);
        intent.putExtra("idChapter",chapter.getId());
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setDataChapters();
        chapterAdapter.setData(mChapters);
    }
}