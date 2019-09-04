package com.example.p478;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    CountHandler countHandler;
    Button button;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        countHandler = new CountHandler();
        handler = new Handler();
    }
    Runnable r = new Runnable() {
        @Override
        public void run() {
            for (int i = 1; i <= 10; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("[T]", "######" + i);
                Message message = countHandler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("cnt",i);
                message.setData(bundle);
                countHandler.sendMessage(message);
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    button.setEnabled(true);
                }
            });
        }
    };

    class CountHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            Bundle bundle = msg.getData();
            int value = bundle.getInt("cnt");
            textView.setText(value+"");
        }
    }

    public void clickBt(View view){
        Thread t = new Thread(r);
        t.start();
        button.setEnabled(false);
    }
    public void clickBt2(View view){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("dialog");
        dialog.setMessage("5 seconds ....");
        dialog.setPositiveButton("NEXT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                progressDialog.show();
                // 5초 후에 실행됨.
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss(); // 5초 후에 progress dialog 사라지게 함.
                        textView.setText("NEXT OK");
                    }
                }, 5000);
            }
        });
        dialog.show();
    }
}
