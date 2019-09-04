package com.example.p499;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textView, textView2, textView3;
    TextView textSpeed, textRPM, textStatus;
    Button startBt, stopBt;
    Random random;
    SpeedTask speedTask;
    RpmTask rpmTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textSpeed = findViewById(R.id.textView4);
        textRPM = findViewById(R.id.textView5);
        textStatus = findViewById(R.id.textView6);
        startBt = findViewById(R.id.button);
        stopBt = findViewById(R.id.button2);
        random = new Random();
    }
    public void startBt(View view){
        startBt.setEnabled(false);
        stopBt.setEnabled(true);
        speedTask = new SpeedTask();
        speedTask.execute();
        rpmTask = new RpmTask();
        rpmTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
    public void stopBt(View view){
        startBt.setEnabled(true);
        stopBt.setEnabled(false);
        speedTask.cancel(true);
        rpmTask.cancel(true);
    }
    class SpeedTask extends AsyncTask<Integer,Integer, Integer>{

        public SpeedTask() {
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            int result = 0;
            int speed = 0;
            while(true){
                if(isCancelled() == true){
                    break;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                speed = 100*(random.nextInt(3))+10*(random.nextInt(10))+random.nextInt(10);
                publishProgress(speed);
                result = speed;

            }
            return result;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            textSpeed.setText(values[0].intValue()+"");
            if(values[0] < 100){
                textStatus.setText("저속");
            }else if(values[0] < 200){
                textStatus.setText("정속");
            }else {
                textStatus.setText("과속");
            }
        }

        @Override
        protected void onPostExecute(Integer integer) {

        }
    }
    class RpmTask extends AsyncTask<Integer,Integer,Integer>{
        public RpmTask() {
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            int result = 0;
            int rpm = 0;
            while(true){
                if(isCancelled() == true){
                    break;
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                rpm = 1000*(random.nextInt(3))+100*(random.nextInt(10));
                publishProgress(rpm);
                result = rpm;
            }
            return result;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            textRPM.setText(values[0].intValue()+"");
        }

        @Override
        protected void onPostExecute(Integer integer) {

        }
    }

}
