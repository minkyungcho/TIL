package com.example.p488;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textView, textView2, textView3;
    TextView textSpeed, textRPM, textAverage;
    Button button;
    Random random;
    MyHandler myHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textSpeed = findViewById(R.id.textView4);
        textRPM = findViewById(R.id.textView5);
        textAverage = findViewById(R.id.textView6);
        button = findViewById(R.id.button);
        myHandler = new MyHandler();
        random = new Random();
    }

    public void clickBt(View view){
        button.setText("STOP");
        button.setEnabled(false);
        Thread t = new Thread(r);
        t.start();
    }
    class MyHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            Bundle bundle1 = msg.getData();
            Bundle bundle2 = msg.getData();
            float spe = bundle1.getInt("spe");
            float rp = bundle2.getInt("rp");
            textSpeed.setText((int)spe+"");
            textRPM.setText((int)rp+"");
        }
    }

    Runnable r = new Runnable() {
        @Override
        public void run() {
            // SPEED
            while(true){
                int speed = Integer.parseInt(textSpeed.getText().toString());
                int rpm = Integer.parseInt(textRPM.getText().toString());

                int ranS = random.nextInt(9)+1;
                int ranR = random.nextInt(99)+1;
                if(speed > 220){
                    speed -= ranS;
                }else if(speed <= 0){
                    speed += ranS;
                }else if(ranS < 5){
                    speed -= ranS;
                }else {
                    speed += ranS;
                }

                if(rpm > 3000){
                    rpm -= ranR;
                }else {
                    rpm += ranR;
                }

                float average = 0;
                if(speed != 0){
                    average = rpm/speed;
                }

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final int spe = speed;
                final int rp = rpm;
                final float av = average;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Bundle bundle1 = new Bundle();
//                        Bundle bundle2 = new Bundle();
//                        Bundle bundle3 = new Bundle();
                        Message message1 = myHandler.obtainMessage();
                        Message message2 = myHandler.obtainMessage();
                        Message message3 = myHandler.obtainMessage();
                        bundle1.putInt("spe", spe);
                        bundle1.putInt("rp", rp);
                        //bundle3.putInt("spe", spe);
                        message2.setData(bundle1);
//                        message2.setData(bundle1);
//                        myHandler.sendMessage(message1);
                        myHandler.sendMessage(message2);
                        textAverage.setText(av+"");
                    }
                });
            }


        }
    };

}
