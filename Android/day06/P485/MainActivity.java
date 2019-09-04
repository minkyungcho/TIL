package com.example.p485;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    MyThread myThread;
    MyHandler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        myThread = new MyThread();
        myHandler = new MyHandler();
    }
    public void clickBt(View view){
        myThread.setCnt(20);
        myThread.start();
//        Message message = Message.obtain();
//        message.obj = 10;
//        myThread.threadHandler.sendMessage(message); // 메인쓰레드에서 서브쓰레드에 메시지 전송
    }

    class MyHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            Bundle bundle = msg.getData();
            int cnt = bundle.getInt("cnt");
            textView.setText(cnt +"");
        }
    }

    class MyThread extends Thread{
        int cnt;
//        ThreadHandler threadHandler;
        public MyThread(){
//            threadHandler = new ThreadHandler();
//            this.cnt = cnt;
        }
        public void setCnt(int cnt){
            this.cnt = cnt;
        }
        @Override
        public void run() {
            Looper.prepare();
            Looper.loop();
        }
        class ThreadHandler extends Handler {
            @Override
            public void handleMessage(@NonNull Message msg) { // 메세지 받는곳
                int data = (int) msg.obj;
                for(int i=0; i<cnt; i++){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d("[T2]", "--------------"+i);
                    final int temp = i;
                    Message message = myHandler.obtainMessage();
                    Bundle bundle = new Bundle();
                    bundle.putInt("cnt",temp);
                    message.setData(bundle);
                    myHandler.sendMessage(message);
                    // i값에 100을 곱해서 TextView에 출력하시오.
//                    final int temp = i;
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            textView.setText((temp)*100+"");
//                        }
//                    });
                }
            }
        }
    }
}
