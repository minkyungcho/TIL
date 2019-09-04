package com.example.p458;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.addJavascriptInterface(new JS(), "js"); // 자바스크립트 JS()를 js로 등록
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("http://m.naver.com"); // 보안문제 발생
    }
    // eclipse에서 작성한 함수 실행하기.
    final class JS{ // 변경할 수 없는 class
        JS(){}
        @android.webkit.JavascriptInterface // javascript 활용할때
        public void webclick(String str){
            textView.setText(str);
            Toast.makeText(MainActivity.this, ""+str, Toast.LENGTH_SHORT).show();
        }
    }
    public void clickBt(View view){
        if(view.getId() == R.id.button){
            webView.loadUrl("http://70.12.60.111/webview");
        }else if(view.getId() == R.id.button2){
//            webView.loadUrl("https://sports.news.naver.com");
            webView.loadUrl("javascript:s('...add...')"); // s 함수를 호출하여 함수가 실행된 후 web이 보이도록.
        }else if(view.getId() == R.id.button3){
            webView.loadUrl("https://entertain.naver.com");
        }
    }
}
