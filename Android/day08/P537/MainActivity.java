package com.example.p537;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textMain = findViewById(R.id.textMain);
        Intent intent = getIntent();
        String str = intent.getStringExtra("ID");
        textMain.setText(str+"님 환영합니다.");
    }

}
