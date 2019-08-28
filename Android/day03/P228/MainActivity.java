package com.example.p228;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
    }
    // progressbar 제어
    public void bar(View view){
        if(view.getId() == R.id.button){
            progressBar.setProgress(progressBar.getProgress()+10);
        }else if(view.getId() == R.id.button2){
            progressBar.setProgress(progressBar.getProgress()-10);
        }
    }
    // progress dialog 제어
    public void dialog(View view){
        if(view.getId() == R.id.button3){
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Progress...");
            progressDialog.setCancelable(false);
            progressDialog.setButton(progressDialog.BUTTON_NEGATIVE, "Cancle", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    progressDialog.dismiss();
                }
            });
            progressDialog.show();
        }else if(view.getId() == R.id.button4){
            progressDialog.dismiss();
        }
    }
}
