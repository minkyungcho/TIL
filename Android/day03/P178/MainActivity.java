package com.example.p178;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button bt;
    RadioButton radioButton, radioButton2;
    CheckBox checkBox, checkBox2;
    Switch switch1;
    ToggleButton toggleButton;

    EditText editText, editText2, editText3, editText4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = findViewById(R.id.button);
        radioButton = findViewById(R.id.radioButton);
        radioButton2 = findViewById(R.id.radioButton2);
        checkBox = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);
        switch1 = findViewById(R.id.switch1);
        toggleButton = findViewById(R.id.toggleButton);

        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);

        editText2.setFocusable(true);

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b == true){
                    editText.setHint("typing your name");
                }else{
                    editText.setHint("");
                }
            }
        });

        // .setOnCheckedChangeListener(). ()안에서 switch 이벤트 처리.switch에 이벤트라 발생하면 ()가 처리.
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // switch on/off 제어
                if(b == true){
                    bt.setBackgroundColor(Color.RED);
                }else {
                    bt.setBackgroundColor(Color.BLUE);
                }
                //bt.setBackgroundColor(Color.RED);
            }
        });

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    // 바깥쪽 판떼기에 Toast 띄우기.
                    Toast.makeText(MainActivity.this, "ToggleBT ON", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "ToggleBT OFF", Toast.LENGTH_SHORT).show();
                }
            }
        });


        bt.setOnClickListener(this); // bt에 이벤트가 들어오면, this(내가=main) 처리한다. bt에 onclick 처리한다.

    }

    @Override
    public void onClick(View view) {
        //checkBox.isChecked();
        Toast.makeText(this, "Click"+checkBox.isChecked()+radioButton.isChecked(), Toast.LENGTH_SHORT).show();
    }
}
