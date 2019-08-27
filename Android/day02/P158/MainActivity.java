package com.example.p158;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import static java.lang.Compiler.disable;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    ConstraintLayout logLayer, registerLayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUi();
    }

    private void setUi() {
        img = findViewById(R.id.img);
        logLayer = findViewById(R.id.logLayer);
        registerLayer = findViewById(R.id.registerLayer);
        // registerLayer.setVisibility(View.INVISIBLE);
        disable();
    }

    public void disable(){
        logLayer.setVisibility(View.INVISIBLE);
        registerLayer.setVisibility(View.INVISIBLE);
    }

    public void clickBt(View view){
        if(view.getId() == R.id.bt1){
            img.setImageResource(R.drawable.cat1);
        }else if(view.getId() == R.id.bt2) {
            img.setImageResource(R.drawable.cat2);
        }else if(view.getId() == R.id.bt3){
            logLayer.setVisibility(View.VISIBLE);
            registerLayer.setVisibility(View.INVISIBLE);
        }else if(view.getId() == R.id.bt4){
            logLayer.setVisibility(View.INVISIBLE);
            registerLayer.setVisibility(View.VISIBLE);
        }
    }
}
