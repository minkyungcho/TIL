package com.example.p217;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toast(View view){

        LayoutInflater inflater = getLayoutInflater();
        View tview = inflater.inflate(R.layout.toast,
                (ViewGroup) findViewById(R.id.tlayout)); // 만든 XML과 판떼기.
        Toast toast = new Toast(this);
        toast.setGravity(Gravity.CENTER, 0, -100);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(tview);
        toast.show();
    }
    public void snack(View view){

    }
    public void dialog(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setTitle("my dialog");
        // builder.setMessage("Do you Exit App ?");
        //builder.setIcon(R.drawable.icon1);
        LayoutInflater inflater = getLayoutInflater();
        View dview = inflater.inflate(R.layout.dialog, (ViewGroup) findViewById(R.id.dlayout));
        TextView tv = dview.findViewById(R.id.textView2);
        tv.setText("DO you wanna Exit App...?");
        builder.setView(dview);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "NO", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }
    public void progress(View view){

    }
}
