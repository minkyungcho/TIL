package com.example.p168;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imgUp;
    ImageView imgDown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUi();
    }

    private void setUi() {
        imgUp = findViewById(R.id.imgUp);
        imgDown = findViewById(R.id.imgDown);
        imgUp.setImageResource(R.drawable.cat1);
    }

    public void clickBt(View view){
        if(view.getId() == R.id.btDown){
            imgUp.setVisibility(View.INVISIBLE);
            imgDown.setVisibility(View.VISIBLE);
            imgDown.setImageResource(R.drawable.cat1);
        }else if(view.getId() == R.id.btUp){
            imgDown.setVisibility(View.INVISIBLE);
            imgUp.setVisibility(View.VISIBLE);
        }
    }

}
