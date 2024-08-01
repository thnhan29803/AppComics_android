package edu.huflit.truyentranh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.huflit.truyentranh.databasedoctruyen.databasedoctruyen;
import edu.huflit.truyentranh.model.Chapter;

public class MainAddTruyenTranh extends AppCompatActivity {

    private Button btnBack, btnAdd;
    private EditText edtName, edtUrl;

    private databasedoctruyen databasedoctruyen;
    private boolean modeView = false;

    Chapter chapter = null;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add_truyen_tranh);
        databasedoctruyen = new databasedoctruyen(this);

        btnBack = findViewById(R.id.btnBack);
        btnAdd = findViewById(R.id.btnAdd);
        edtName = findViewById(R.id.edtNameChaper);
        edtUrl = findViewById(R.id.edtUrl);

        id = getIntent().getIntExtra("idtruyen",-1);
        int idchapter = getIntent().getIntExtra("idChapter",-1);


        //Nếu có truyện đã có chapter hiện thị ra chapter truyện
        if (idchapter > -1){
            chapter = databasedoctruyen.getChapter(idchapter);
            edtName.setText(chapter.getNameChapter());
            edtUrl.setText(chapter.getUrl());
        }
        else {
            modeView = true;
        }

        btnBack.setOnClickListener(view -> {
            finish();
        });

        btnAdd.setOnClickListener(view -> {
            String name = edtName.getText().toString();
            String url = edtUrl.getText().toString();
            if(name.isEmpty() || url.isEmpty()) {
                Toast.makeText(this,"Không được bỏ trống thông tin",Toast.LENGTH_SHORT).show();
            } else {
                if (modeView) {
                    if (id != -1)
                   databasedoctruyen.addChapter(new Chapter(id,name,url));
                    finish();
                } else {
                    databasedoctruyen.updateChapter(new Chapter(idchapter,chapter.getIdTruyen(),name,url));
                    finish();
                }
            }
        });
    }
}