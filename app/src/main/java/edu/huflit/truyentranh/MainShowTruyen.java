package edu.huflit.truyentranh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainShowTruyen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_show_truyen);

        //Lấy dữ liệu URL được gửi bên trang Chapter
        String url = getIntent().getStringExtra(MainChapter.KEY_URL);


        WebView webView = findViewById(R.id.webView);
        // Phương thức chỉ định mọi đường dẫn nhấn vào sẽ được tải lên và hiện thị bởi WebView
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setJavaScriptEnabled(true);
        //Phương thức load đường dẫn
        webView.loadUrl(url);

    }
}