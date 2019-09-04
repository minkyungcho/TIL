package com.example.p490;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button, button2, button3;
    TextView textView;
    ProgressBar progressBar;
    ProgressDialog progressDialog;
    MyTask myTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        textView = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar);
        progressDialog = new ProgressDialog(this);

    }

    // task 시작
    public void clickBt(View view){
        myTask = new MyTask(30);
        myTask.execute();
    }
    // task 정지
    public void clickBt2(View view){
        myTask.cancel(true);
    }
    // task 재시작
    public void clickBt3(View view){
        myTask = new MyTask(30);
        myTask.execute();
    }

    class MyTask extends AsyncTask<Integer, Integer, String>{
        int cnt;

        public MyTask(int cnt){
            this.cnt = cnt;
        }

        // thread 동작 전
        @Override
        protected void onPreExecute() {
            progressBar.setMax(cnt);
            button.setEnabled(false);
            textView.setText("START TASK");
            progressDialog.setTitle("Progress");
            progressDialog.show();
            //progressDialog.setCancelable(false);
        }

        // thread 동작되는 부분. 끝나면서 String return
        @Override
        protected String doInBackground(Integer... integers) {
            String result ="";
            int sum =0;
            for(int i=1; i<=cnt; i++){
                if(isCancelled() == true){
                    break;
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sum += i;
                publishProgress(i);
            }
            result = sum + "";
            return result;
        }

        // thread에서 발생되고 있는 내용을 받아서 그때그때 처리
        @Override
        protected void onProgressUpdate(Integer... values) {
            // publishProgress(i); 의 i값이 values 배열로 들어온다.
            progressBar.setProgress(values[0].intValue());
            textView.setText(values[0].intValue()+"");
        }

        // thread 종료 후
        @Override
        protected void onPostExecute(String s) {
            button.setEnabled(true);
            textView.setText("STOP TASK : "+s);
            progressDialog.dismiss();
        }
    }
}
