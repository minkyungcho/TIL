package com.example.p577;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView date, title, contents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        date = findViewById(R.id.textView3);
        title = findViewById(R.id.textView4);
        contents = findViewById(R.id.textView5);
        Intent i = getIntent();
        String d = i.getStringExtra("td");
        String t = i.getStringExtra("tt");
        String c = i.getStringExtra("tc");
        date.setText(d);
        title.setText(t);
        contents.setText(c);
    }

    public void back(View view){
        finish();
    }


}
